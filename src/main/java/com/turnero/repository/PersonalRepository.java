package com.turnero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	Optional<Personal> findById(Integer id);
    
    Optional<Personal> findByEmail(String email);

}
