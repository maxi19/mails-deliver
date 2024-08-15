package com.turnero.component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.turnero.config.AuthConfig;
import com.turnero.dto.Enviable;
import com.turnero.dto.ItemEnviable;
import com.turnero.enums.Estado;
import com.turnero.service.EmailService;
import com.turnero.service.ReciboService;

@Component
public class SenderServiceImp implements SenderService{

	@Value("${config.path.recibos}")
	private String path;

	@Autowired
	private AuthConfig config;
	
	@Autowired
	private EmailService emailService;
	
    @Autowired
    private ReciboService reciboService;
	
	private static final Logger log =  LoggerFactory.getLogger(SenderServiceImp.class);


	public Properties setearConfiguracion(Enviable enviable) {
		Properties props = new Properties();
		props.put("mail.smtp.host", enviable.smtpHost());
		props.put("mail.smtp.port", enviable.smtpPort());
		props.put("mail.smtp.ssl.enable", enviable.smtpEnable());
		props.put("mail.smtp.auth", enviable.smtpAuth());
		return props;
	}


	@Async
	@Override
	public CompletableFuture<Enviable> enviarRecibos(Enviable enviable) throws Exception {
			long start = System.currentTimeMillis();
			
			config.setearConfiguracion(enviable.getFromEmail(), enviable.getSecret());
			
		    Session session = Session.getDefaultInstance(setearConfiguracion(enviable), config );
		    
		    Estado estadoEmail = null;
		    		    
		    StringBuffer buffer = new  StringBuffer();
		    
		    try {
		    	//creamos email
 		      Message msg = new MimeMessage(session);

		      msg.setFrom(new InternetAddress(enviable.getFromEmail()));
			  msg.setRecipient(RecipientType.TO,  new InternetAddress(enviable.getToEmail()));
		      msg.setSubject(enviable.getSubject());
			  
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
				  		MimeBodyPart attachment = generarAdjunto(item);
				  		mp.addBodyPart(attachment);
				  	}
			  }else {
				  MimeBodyPart attachment = generarAdjunto(enviable.getItems().get(0));
				  mp.addBodyPart(attachment);
			  }

			  msg.setContent(mp);
			  //enviamos
			  Transport.send(msg);
			  long end = System.currentTimeMillis();
			  estadoEmail = Estado.ENVIADO;
			  
			  
			  
			  log.info("tiempo total {} para el mail {}", (end -start), enviable.getToEmail());
			} catch (AddressException e) {
				estadoEmail = Estado.ERROR;
				buffer.append("La direccion " + enviable.getToEmail() +" es invalida o no se pudo enviar");
				log.error("La direccion {} es invalida o no se pudo enviar",enviable.getToEmail());
				
			} catch (MessagingException e ) {
				estadoEmail = Estado.ERROR;
				buffer.append("Errpr em el mensaje para el mail " + enviable.getToEmail());
				log.error("error en el mensaje para el mail {}", enviable.getToEmail());
		    } catch (UnsupportedEncodingException e) {
				estadoEmail = Estado.ERROR;
				buffer.append(e.getCause().toString());
                throw new RuntimeException(e);
            } catch (IOException e) {
            	buffer.append(e.getMessage());
                throw new RuntimeException(e);
            }finally {
            	emailService.persistirEmail(
            			enviable.getToEmail(),
            			enviable.getFromEmail(),
            			enviable.getSubject(),
            			"",
            			enviable.getItems().toString(),
            			estadoEmail,
            			buffer.toString());
            }
		    
		    return CompletableFuture.completedFuture(enviable);
		  }


	private MimeBodyPart generarAdjunto(ItemEnviable item) throws IOException, MessagingException {
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.attachFile(new File(item.getFilesPath()), MediaType.APPLICATION_PDF_VALUE, null);
		attachment.setFileName(item.getFilesName());
		return attachment;
	}

}




