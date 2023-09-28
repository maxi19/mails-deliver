package com.turnero.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.turnero.entity.Personal;

public interface PersonalRepository extends CrudRepository<Personal, UUID>{


    Optional<Personal> findById(UUID id);
    Optional<Personal> findByEmail(String email);
	
}
