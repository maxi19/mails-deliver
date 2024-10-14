package com.turnero.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Registro;
import com.turnero.enums.Estado;
import com.turnero.repository.RegistroRepository;

@Service
public class RegistroServiceImp implements RegistroService{

	@Autowired
	private RegistroRepository registroRepository;

	@Transactional
	@Override
	public void persistirRegistro(String identificador, String email) throws Exception {
			if (email.isBlank() || email.isEmpty()) {
				registroRepository.save( new Registro(identificador, email, Estado.NOIDENTIFICADO));			
			}
			registroRepository.save( new Registro(identificador, email, Estado.IDENTIFICADO ));			
	}

	@Override
	public Optional<Registro>  buscarPorIdentificador(String identificador) throws Exception {
		Optional<Registro> optRegistro = registroRepository.findByIdentificador(identificador);
		if (!optRegistro.isEmpty()) {
			return optRegistro;
		}
		throw new Exception ("No se encontro registro");
	}
	
	
	
	
	
	
	
}
