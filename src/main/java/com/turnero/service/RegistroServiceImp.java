package com.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Registro;
import com.turnero.repository.RegistroRepository;

@Service
public class RegistroServiceImp implements RegistroService{

	@Autowired
	private RegistroRepository registroRepository;

	@Override
	public void persistirRegistro(String identificador, String email) throws Exception {
			if (email.isBlank() || email.isEmpty()) {
				registroRepository.save( new Registro(identificador, email, "SIN IDENTIFICADO" ));			
			}
			registroRepository.save( new Registro(identificador, email, "IDENTIFICADO" ));			
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
