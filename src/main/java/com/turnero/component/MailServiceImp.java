package com.turnero.component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.turnero.dto.Enviable;
import com.turnero.dto.ItemEnviable;

@Component
public class MailServiceImp implements MailService{

	@Value("${config.path.recibos}")
	private String path;

	private Authenticator config;
	
	private static final Logger log =  LoggerFactory.getLogger(MailServiceImp.class);


	public Properties setearConfiguracion(Enviable enviable) {
		Properties props = new Properties();
		props.put("mail.smtp.host", enviable.smtpHost());
		props.put("mail.smtp.port", enviable.smtpPort());
		props.put("mail.smtp.ssl.enable", enviable.smtpEnable());
		props.put("mail.smtp.auth", enviable.smtpAuth());
		return props;
	}



	@Override
	public void enviarRecibos(Enviable enviable) {
			this.config = new Authenticator() {
			@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					PasswordAuthentication p = new PasswordAuthentication(enviable.getFromEmail(),enviable.getSecret());
					return p;
				}
			};
		    Session session = Session.getDefaultInstance(setearConfiguracion(enviable), config);
		    try {
		    	//creamos email
		      Message msg = new MimeMessage(session);

		      msg.setFrom(new InternetAddress("maximilianoguzman@fatimarem.edu.ar"));
			  msg.setRecipient(RecipientType.TO,  new InternetAddress("maximilianoguzman@fatimarem.edu.ar"));
		      msg.setSubject("Recibos De sueldo");
			  
		      //BODY
		      MimeBodyPart textPart = new MimeBodyPart();
			  textPart.setText("");
			  Multipart mp = new MimeMultipart();
			  mp.addBodyPart(textPart);

			  String htmlBody = "";
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
				log.error("error en el mensaje para el mail {}", enviable.getToEmail());
		    } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
  			  //reciboRepository.save(enviable.getFile());
			}

		  }

	private void buildHeaderEmail(Enviable enviable, Message msg) throws MessagingException, AddressException {
		msg.setFrom(new InternetAddress(enviable.getFromEmail()));
		  msg.addRecipient(Message.RecipientType.TO, new InternetAddress(enviable.getToEmail()));
		  msg.setSubject(enviable.getSubject());
	}




}




