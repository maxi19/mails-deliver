package com.turnero.repository;

import com.turnero.entity.ReciboEnviado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReciboEnviadoRepository extends CrudRepository<ReciboEnviado, Long> {
}
