package com.turnero.repository;

import com.turnero.entity.ReciboEnviado;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReciboEnviadoRepository extends CrudRepository<ReciboEnviado, UUID> {
}
