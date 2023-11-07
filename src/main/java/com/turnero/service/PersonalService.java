package com.turnero.service;

import com.turnero.entity.Personal;

import java.util.List;
import java.util.UUID;

public interface PersonalService {

	public void Add(Personal personal) throws Exception;

	public void eliminarPersonal(Integer id) throws Exception;

	public Personal buscarPersonal(Integer id) throws Exception;

	public List<Personal> listar();

	public void editar(Personal personal, Integer id) throws Exception;
	
}
