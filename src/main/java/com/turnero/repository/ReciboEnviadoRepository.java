package com.turnero.repository;

import com.turnero.dto.ReciboEnviado;
import com.turnero.service.MailServiceImp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReciboEnviadoRepository extends CrudRepository<ReciboEnviado, Integer> {

    Optional<ReciboEnviado> findById(Integer id);
}
