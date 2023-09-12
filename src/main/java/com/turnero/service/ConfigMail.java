package com.turnero.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigMail extends Authenticator {

	@Value("${config.mail.destinatario}")
	private String mail;
	
	@Value("${config.mail.password}")
	private String password ;
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication p = new PasswordAuthentication(mail,password);
		return p;
	}

	
	
}
