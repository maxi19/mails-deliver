package com.turnero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.repository.PersonalRepository;

@Service
public class PersonalServiceImp implements PersonalService {

	@Autowired
	private PersonalRepository personalRepository;
	
	
	@Override
	public void Add(Personal personal) throws Exception {
		personalRepository.save(personal);
	}

}
