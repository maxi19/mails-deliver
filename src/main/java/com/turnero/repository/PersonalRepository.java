package com.turnero.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Personal;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer>{


    @Override
	Optional<Personal> findById(Integer id);
    Optional<Personal> findByEmail(String email);

}
