package com.turnero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, Integer> {

}
