package com.turnero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnero.entity.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {

	
	public Optional<Registro> findByIdentificador(String identificador);
	
	
	
}
