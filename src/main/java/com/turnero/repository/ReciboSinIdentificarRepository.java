package com.turnero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.turnero.entity.ReciboSinIdentificar;

public interface ReciboSinIdentificarRepository extends CrudRepository<ReciboSinIdentificar, UUID> {

}
