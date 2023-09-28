package com.turnero.repository;

import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReciboRepository extends CrudRepository<Recibo, UUID> {
}
