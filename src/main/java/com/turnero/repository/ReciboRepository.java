package com.turnero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turnero.entity.Recibo;
import com.turnero.enums.Estado;

@Repository
public interface ReciboRepository extends CrudRepository<Recibo, Integer>{

	//@Query("SELECT u FROM Recibo u WHERE u.nombre = ?1")
	Optional<Recibo> findByNombre(String nombre);

	Optional<List<Recibo>> findByEmailAndEstado(String email, Estado estado);
	
	
	@Query("SELECT u FROM Recibo u WHERE u.estado = ?1")
	public Optional<List<Recibo>> findByEstado(Estado estado);

	//@Query("SELECT u FROM Recibo u WHERE u.estado = ?1 and u.usuario = ?2")
	public Optional<List<Recibo>> findByEstadoAndUsuario(Estado estado,String usuario);

}
