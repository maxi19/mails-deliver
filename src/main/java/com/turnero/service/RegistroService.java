package com.turnero.service;

import java.util.Optional;

import com.turnero.entity.Registro;

public interface RegistroService {

	public void persistirRegistro(String identificador, String email ) throws Exception;
	
	public Optional<Registro>  buscarPorIdentificador(String identificador) throws Exception;
	
}
