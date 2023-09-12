package com.turnero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.turnero.entity.Personal;

public interface RegistroRepository extends CrudRepository<Personal, UUID> {

	
	
}
