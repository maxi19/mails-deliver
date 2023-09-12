package com.turnero.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MailService {

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
	
	@Bean
	public Properties getProperties() {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.port", smtpPort);
	    props.put("mail.smtp.ssl.enable", smtpEnable);
	    props.put("mail.smtp.auth", smtpAuth);
	    return props;
	}
	
	
	  public void sendSimpleMail() {
		    Session session = Session.getDefaultInstance(getProperties(), config);
		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress("maxi19@gmail.com", "Saludental.com Admin"));
		      msg.addRecipient(Message.RecipientType.TO,
		                       new InternetAddress("maxi19@gmail.com", "Mr. User"));
		      msg.setSubject("Your Example.com account has been activated");
		      msg.setText("This is a test");
		      Transport.send(msg);
		    } catch (AddressException e) {
		    	System.out.println(e.getMessage());
		    } catch (MessagingException e) {
		    	System.out.println(e.getMessage());
		    } catch (UnsupportedEncodingException e) {
		      // ...
		    }
		    // [END simple_example]
		  }

		  public void sendMultipartMail() {
		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);

		    String msgBody = "...";

		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
		      msg.addRecipient(Message.RecipientType.TO,
		                       new InternetAddress("user@example.com", "Mr. User"));
		      msg.setSubject("Your Example.com account has been activated");
		      msg.setText(msgBody);

		      // [START multipart_example]
		      String htmlBody = "";          // ...
		      byte[] attachmentData = null;  // ...
		      Multipart mp = new MimeMultipart();

		      MimeBodyPart htmlPart = new MimeBodyPart();
		      htmlPart.setContent(htmlBody, "text/html");
		      mp.addBodyPart(htmlPart);

		      MimeBodyPart attachment = new MimeBodyPart();
		      InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
		      attachment.setFileName("manual.pdf");
		      attachment.setContent(attachmentDataStream, "application/pdf");
		      mp.addBodyPart(attachment);

		      msg.setContent(mp);
		      // [END multipart_example]

		      Transport.send(msg);

		    } catch (AddressException e) {
		      // ...
		    } catch (MessagingException e) {
		      // ...
		    } catch (UnsupportedEncodingException e) {
		      // ...
		    }
		  }
	
}
