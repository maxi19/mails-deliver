package com.turnero.repository;

import com.turnero.entity.ReciboIdentificado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboIdentificadoRepository extends CrudRepository<ReciboIdentificado, Integer> {
}
