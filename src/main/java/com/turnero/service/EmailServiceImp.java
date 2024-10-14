package com.turnero.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Email;
import com.turnero.enums.Estado;
import com.turnero.repository.EmailRepository;

@Service
public class EmailServiceImp implements EmailService{

	@Autowired
	private EmailRepository emailRepository;
	
	@Transactional
	@Override
	public void persistirEmail(String to, String from,String subject,String body, String attachament,  Estado estado, String error) throws Exception {
	
		emailRepository.save(new Email(to, from, subject, body,  attachament,estado, error, LocalDate.now() ));;
		
	}

}
