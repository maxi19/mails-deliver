package com.turnero.repository;

import com.turnero.entity.ReciboEnviado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReciboEnviadoRepository extends CrudRepository<ReciboEnviado, Integer> {

    Optional<ReciboEnviado> findById(Integer id);
}
