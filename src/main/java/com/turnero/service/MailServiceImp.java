package com.turnero.service;

import java.io.*;
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
import com.turnero.dto.ItemDto;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboEnviado;
import com.turnero.entity.ReciboSinIdentificar;
import com.turnero.repository.ReciboEnviadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImp {

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

	  public void sendSimpleMail(Personal personal, ReciboSinIdentificar recibo) {
		    Session session = Session.getDefaultInstance(getProperties(), config);
		    try {
			  String msgBody = "correo de prueba envio de archivo: ";
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress(emailUser, "Secretaria"));
		      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(personal.getEmail(), personal.getEmail()));
		      msg.setSubject("Recibo De Sueldo");
		      msg.setText(msgBody);

			  String UrlRecibo = path.concat("/").concat(recibo.getFileName());
			  String htmlBody = "";
			  Multipart mp = new MimeMultipart();

			  MimeBodyPart htmlPart = new MimeBodyPart();
			  htmlPart.setContent(htmlBody, "text/html");
			  mp.addBodyPart(htmlPart);

			  MimeBodyPart attachment = new MimeBodyPart();
			  attachment.attachFile(new File(UrlRecibo), "application/pdf", null);
			  attachment.setFileName(recibo.getFileName());

			  mp.addBodyPart(attachment);
			  msg.setContent(mp);
			  Transport.send(msg);


			  ReciboEnviado reciboEnviado = new ReciboEnviado();
			  reciboEnviado.setPersonal(personal);
			  reciboEnviado.setFileName(recibo.getFileName());
			//reciboEnviado.setFecha();
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

		  public void sendMultipartMail(List<DocenteDto> docenteDto) {
			Session session = Session.getDefaultInstance(getProperties(), config);
		    String msgBody = "correo de prueba envio de archivo: ";
		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress(emailUser, "Secretaria"));
			  //String htmlBody = "";
			  Multipart mp = new MimeMultipart();
			  //MimeBodyPart htmlPart = new MimeBodyPart();
			  //htmlPart.setContent(htmlBody, "text/html");
			  //mp.addBodyPart(htmlPart);

			  for (DocenteDto docenteDtos :docenteDto) {
				  msg.addRecipient(Message.RecipientType.TO,new InternetAddress(docenteDtos.getEmail(), docenteDtos.getEmail()));
				  msg.setSubject("Your Example.com account has been activated");
				  msg.setText(msgBody);
				  for (ItemDto items: docenteDtos.getItemDto()) {
						MimeBodyPart attachment = new MimeBodyPart();
						attachment.attachFile(new File(path.concat("/").concat(items.getArchivo())), "application/pdf", null);
						attachment.setFileName(items.getArchivo());
						mp.addBodyPart(attachment);

						Date fechaDate = new Date();
					  	Calendar fecha = new GregorianCalendar();
						  String dia = Integer.toString(fecha.get(Calendar.DATE));
						  //String hora = Integer.toString(fecha.get(Calendar.))
					  	ReciboEnviado reciboEnviado = new ReciboEnviado();
					  //reciboEnviado.setPersonal(personal);
					  	reciboEnviado.setFileName(items.getArchivo());
					    reciboEnviado.setFecha(fechaDate);
					  	reciboEnviadoRepository.save(reciboEnviado);
				  }
				  msg.setContent(mp);
				  Transport.send(msg);


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


	
}
