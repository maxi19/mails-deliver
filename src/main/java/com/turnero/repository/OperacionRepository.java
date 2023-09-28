package com.turnero.repository;

import com.turnero.entity.Operacion;
import com.turnero.entity.Personal;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OperacionRepository extends CrudRepository<Operacion, UUID> {
}
