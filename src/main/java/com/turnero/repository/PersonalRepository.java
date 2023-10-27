package com.turnero.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.turnero.entity.Personal;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer>{


    Optional<Personal> findById(Integer id);
    Optional<Personal> findByEmail(String email);
	
}
