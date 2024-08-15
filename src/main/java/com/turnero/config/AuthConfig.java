package com.turnero.config;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.stereotype.Component;

@Component
public class AuthConfig extends Authenticator {

	private  String email;
	
	private  String secret;
	
	
	
	public AuthConfig() {
		super();
		
	}


	public void setearConfiguracion(String email, String secret ) {
		this.email = email;
		this.secret = secret;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication p = new PasswordAuthentication(email,secret);
		return p;
	}

}
