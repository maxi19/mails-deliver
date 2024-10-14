package com.turnero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {

	
	public Optional<Registro> findByIdentificador(String identificador);
	
	
	
}
