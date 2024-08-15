package com.turnero.service;


import com.turnero.enums.Estado;

public interface EmailService {

	public void persistirEmail (String to, String from,String subject,String body, String attachament,  Estado estado, String error) throws Exception;
	
}
