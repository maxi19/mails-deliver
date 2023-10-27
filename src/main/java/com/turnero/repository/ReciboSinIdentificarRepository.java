package com.turnero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.turnero.entity.ReciboSinIdentificar;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboSinIdentificarRepository extends CrudRepository<ReciboSinIdentificar, Integer> {

}
