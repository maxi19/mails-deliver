package com.turnero.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Recibo;
@Repository
public interface ReciboRepository extends CrudRepository<Recibo, Integer>{

	 Optional<Recibo> findByNombre(String nombre);
	
}
