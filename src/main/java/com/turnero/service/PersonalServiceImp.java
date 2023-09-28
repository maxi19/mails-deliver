package com.turnero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.repository.PersonalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalServiceImp implements PersonalService {

	@Autowired
	private PersonalRepository personalRepository;
	
	
	@Override
	public void Add(Personal personal) throws Exception {
		personalRepository.save(personal);
	}

	@Override
	public void editar(Personal personal) throws Exception {

		try {
			Personal personaBuscada  = this.buscarPersonal(personal.getId());
			personaBuscada.setApellidos(personal.getApellidos());
			personaBuscada.setNombres(personal.getNombres());
			personaBuscada.setNombres(personal.getEmail());
			personaBuscada.setNombres(personal.getPatron());
			personalRepository.save(personaBuscada);
		} catch (Exception e) {
			throw new Exception("la persona que quiere editar no existe");
		}

	}
	@Override
	public List<Personal> listar() {
		List<Personal> listado = new ArrayList<Personal>();
		for (Personal personal : personalRepository.findAll()) {
			listado.add(personal);
		}
		return listado;
	}

	@Override
	public Personal buscarPersonal(UUID id) throws Exception {
		Optional<Personal> optPersonal =personalRepository.findById(id);
		if (optPersonal.isEmpty()) {
			throw new Exception("la persona no existe");
		}
		return optPersonal.get();
	}

	@Override
	public void eliminarPersonal(UUID id) throws Exception {
		try {
			Personal personaBuscada  = this.buscarPersonal(id);
			personalRepository.delete(personaBuscada);
		} catch (Exception e) {
			throw new Exception("la persona no existe");
		}
	}

}
