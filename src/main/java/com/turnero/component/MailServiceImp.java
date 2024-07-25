package com.turnero.component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.turnero.dto.DocenteDto;
import com.turnero.dto.Enviable;
import com.turnero.dto.ItemEnviable;
import com.turnero.entity.Recibo;
import com.turnero.exceptions.DeliverException;
import com.turnero.repository.PersonalRepository;
import com.turnero.repository.ReciboRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImp implements MailService{

	@Value("${config.path.recibos}")
	private String path;
	
	@Autowired
	private Authenticator config;
	
	/*
	@Value("${config.smtp.host}")
	private String smtpHost;
	
	@Value("${config.smtp.port}")
	private String smtpPort;
	
	@Value("${config.smtp.enable}")
	private String smtpEnable;
	
	@Value("${config.smtp.auth}")
	private String smtpAuth;
	
	@Value("${config.mail.destinatario}")
	private String emailUser;
	*/
	private static final Logger log =  LoggerFactory.getLogger(MailServiceImp.class);

	@Autowired
	private ReciboRepository reciboRepository;
	

	public Properties setearConfiguracion(Enviable enviable) {
		Properties props = new Properties();
		props.put("mail.smtp.host", enviable.smtpHost());
		props.put("mail.smtp.port", enviable.smtpPort());
		props.put("mail.smtp.ssl.enable", enviable.smtpEnable());
		props.put("mail.smtp.auth", enviable.smtpAuth());
		return props;
	}

	

	public void enviarRecibos(Enviable enviable) {
		    Session session = Session.getDefaultInstance(setearConfiguracion(enviable), config);
		    try {
		    	//creamos email
		      Message msg = new MimeMessage(session);
		      
		      buildHeaderEmail(enviable, msg);
		      
		      	//creamos body terminar
		      MimeBodyPart textPart = new MimeBodyPart();
			  textPart.setText("");
			  
			  String htmlBody = "";
			  Multipart mp = new MimeMultipart();
			  mp.addBodyPart(textPart);

			  MimeBodyPart htmlPart = new MimeBodyPart();
			  htmlPart.setContent(htmlBody,  MediaType.TEXT_HTML_VALUE);
			  mp.addBodyPart(htmlPart);
			  
			  if (enviable.isMultiFile()) {
				  	for(ItemEnviable item : enviable.getItems()){
				  		MimeBodyPart attachment = new MimeBodyPart();
				  		attachment.attachFile(new File(item.getFilesPath()), MediaType.APPLICATION_PDF_VALUE, null);
				  		attachment.setFileName(item.getFilesName());
				  		mp.addBodyPart(attachment);
				  	}
			  }else {
				  String UrlRecibo = enviable.getItems().get(0).getFilesPath();
				  MimeBodyPart attachment = new MimeBodyPart();
				  attachment.attachFile(new File(UrlRecibo), MediaType.APPLICATION_PDF_VALUE, null);
				  attachment.setFileName(enviable.getItems().get(0).getFilesName());
				  mp.addBodyPart(attachment);
			  }

			  msg.setContent(mp);
			  
			  //enviamos
			  Transport.send(msg);
			} catch (AddressException e) {
				log.error("La direccion {} es invalida o no se pudo enviar",enviable.getToEmail());
			} catch (MessagingException e ) {
		    	//throw new DeliverException("Error en mensaje", e);
				log.error("error en el mensaje para el mail {}", enviable.getToEmail());
		    } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
  			  //reciboRepository.save(enviable.getFile());
			}

		  }
/*
		  public void enviarRecibos(Enviable enviable) {
			Session session = Session.getDefaultInstance(getProperties(enviable), config);
		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress(enviable.getFromEmail()));
			  msg.setSubject("Recibos De sueldo:");
			  MimeBodyPart textPart = new MimeBodyPart();
			  textPart.setText("esto es una texto de prueba");
			  Multipart mp = new MimeMultipart();
			  mp.addBodyPart(textPart);
			  msg.addRecipient(Message.RecipientType.TO,new InternetAddress(enviable.getToEmail()));
			  
			  Optional<Personal> docente = personalRepository.findById(docenteDto.getId());
			  List<ItemDto> recibosEnviados = new ArrayList<>();
			  for (ItemDto items: docenteDto.getItemDto()) {
				boolean identificado = false;
				for (ReciboIdentificado reciboIdentificado : reciboIdentificadoRepository.findAll()){
					if(reciboIdentificado.getPersonal().getPersonal_id().equals(docenteDto.getId()) && reciboIdentificado.getNombre().equals(items.getArchivo())){
						  identificado = true;
						  break;
				    }
				}
				if(identificado){
					MimeBodyPart attachment = new MimeBodyPart();
					attachment.attachFile(new File(path.concat("/").concat(items.getArchivo())), "application/pdf", null);
					attachment.setFileName(items.getArchivo());
					mp.addBodyPart(attachment);
					items.setEnviado(Boolean.TRUE);
					recibosEnviados.add(items);
				}
			  }
			  msg.setContent(mp);
			  Transport.send(msg);

			  for (ItemDto recibo: recibosEnviados) {
				ReciboEnviado reciboEnviado = new ReciboEnviado();
				LocalDateTime fecha = LocalDateTime.now();
				reciboEnviado.setPersonal(docente.get());
				reciboEnviado.setNombre(recibo.getArchivo());
				reciboEnviado.setFecha(fecha);
				reciboEnviadoRepository.save(reciboEnviado);
			  }


		    } catch (AddressException e) {
		      // ...
		    } catch (MessagingException e) {
		      // ...
		    } catch (UnsupportedEncodingException e) {
		      // ...
		    } catch (IOException e) {
				throw new RuntimeException(e);
			}
          }
          /*
		  
		  /*
	public void enviarRecibo(DocenteDto docenteDto, int idItem) {
		Session session = Session.getDefaultInstance(getProperties(), config);
		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailUser));
			msg.setSubject("Recibo De sueldo:");
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("esto es una texto de prueba");

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(textPart);

			ItemDto reciboAEnviar = docenteDto.getItemDto().get(idItem);

			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(docenteDto.getEmail()));
			MimeBodyPart attachment = new MimeBodyPart();
			attachment.attachFile(new File(path.concat("/").concat(reciboAEnviar.getArchivo())), "application/pdf", null);
			attachment.setFileName(docenteDto.getItemDto().get(idItem).getArchivo());
			mp.addBodyPart(attachment);

			msg.setContent(mp);
			Transport.send(msg);

			Optional<Personal> docente = personalRepository.findById(docenteDto.getId());
			ReciboEnviado reciboEnviado = new ReciboEnviado();
			LocalDateTime fecha = LocalDateTime.now();
			reciboEnviado.setPersonal(docente.get());
			reciboEnviado.setNombre(docenteDto.getItemDto().get(idItem).getArchivo());
			reciboEnviado.setFecha(fecha);
			reciboEnviadoRepository.save(reciboEnviado);

		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (UnsupportedEncodingException e) {
			// ...
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	*/



	private void buildHeaderEmail(Enviable enviable, Message msg) throws MessagingException, AddressException {
		msg.setFrom(new InternetAddress(enviable.getFromEmail()));
		  msg.addRecipient(Message.RecipientType.TO, new InternetAddress(enviable.getToEmail()));
		  msg.setSubject(enviable.getSubject());
	}




}
