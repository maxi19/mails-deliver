package com.turnero.service;

import java.io.*;
import java.time.LocalDateTime;
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
import com.turnero.dto.EnvioSinMatchDto;
import com.turnero.dto.ItemDto;
import com.turnero.entity.ReciboEnviado;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboIdentificado;
import com.turnero.repository.PersonalRepository;
import com.turnero.repository.ReciboEnviadoRepository;
import com.turnero.repository.ReciboIdentificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImp implements MailService{

	@Value("${config.path.recibos}")
	private String path;
	@Autowired
	private Authenticator config;
	
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
	
	@Bean
	public Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.ssl.enable", smtpEnable);
		props.put("mail.smtp.auth", smtpAuth);
		return props;
	}
	@Autowired
	private ReciboEnviadoRepository reciboEnviadoRepository;

	@Autowired
	private PersonalRepository personalRepository;

	@Autowired
	private ReciboIdentificadoRepository reciboIdentificadoRepository;

	  public void enviarSinMatch(EnvioSinMatchDto envioSinMatchDto) {
		    Session session = Session.getDefaultInstance(getProperties(), config);
		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress(emailUser));
		      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(envioSinMatchDto.getPersonal().getEmail()));
		      msg.setSubject("Recibo De Sueldo");
		      MimeBodyPart textPart = new MimeBodyPart();
			  textPart.setText("");

			  String UrlRecibo = path.concat("/").concat(envioSinMatchDto.getReciboSinIdentificar().getNombre());
			  String htmlBody = "";
			  Multipart mp = new MimeMultipart();
			  mp.addBodyPart(textPart);

			  MimeBodyPart htmlPart = new MimeBodyPart();
			  htmlPart.setContent(htmlBody, "text/html");
			  mp.addBodyPart(htmlPart);

			  MimeBodyPart attachment = new MimeBodyPart();
			  attachment.attachFile(new File(UrlRecibo), "application/pdf", null);
			  attachment.setFileName(envioSinMatchDto.getReciboSinIdentificar().getNombre());

			  mp.addBodyPart(attachment);
			  msg.setContent(mp);
			  Transport.send(msg);

			  ReciboEnviado reciboEnviado = new ReciboEnviado();
			  reciboEnviado.setPersonal(envioSinMatchDto.getPersonal());
			  reciboEnviado.setNombre(envioSinMatchDto.getReciboSinIdentificar().getNombre());
			  LocalDateTime fecha = LocalDateTime.now();
			  reciboEnviado.setFecha(fecha);
			  reciboEnviadoRepository.save(reciboEnviado);


			} catch (AddressException e) {
		    	System.out.println(e.getMessage());
		    } catch (MessagingException e) {
		    	System.out.println(e.getMessage());
		    } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
          // [END simple_example]
		  }

		  public void enviarRecibos(DocenteDto docenteDto) {
			Session session = Session.getDefaultInstance(getProperties(), config);
		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress(emailUser));
			  msg.setSubject("Recibos De sueldo:");
			  MimeBodyPart textPart = new MimeBodyPart();
			  textPart.setText("esto es una texto de prueba");
			  Multipart mp = new MimeMultipart();
			  mp.addBodyPart(textPart);
			  msg.addRecipient(Message.RecipientType.TO,new InternetAddress(docenteDto.getEmail()));
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



}
