package com.turnero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.exceptions.DeliverException;
import com.turnero.repository.PersonalRepository;

@Service
public class PersonalServiceImp implements PersonalService {

	@Autowired
	private PersonalRepository personalRepository;


	@Transactional
	@Override
	public void Add(Personal personal) throws Exception {
		if (!personalRepository.findByEmail(personal.getEmail()).isEmpty()) {
			throw new DeliverException("Ya existe un usuario con este email");
		}
		personalRepository.save(personal);
	}

	@Transactional
	@Override
	public void editar(Personal personal, Integer id) throws Exception {

		try {
			Personal personalEncontrada  = this.buscarPersonal(id);

			personalEncontrada.setApellidos(personal.getApellidos().toUpperCase());
			personalEncontrada.setNombres(personal.getNombres().toUpperCase());
			personalEncontrada.setEmail(personal.getEmail());
			personalEncontrada.setPatron(personal.getPatron());
			personalRepository.save(personalEncontrada);
		} catch (Exception e) {
			throw new Exception("la persona que quiere editar no existe o no fue encontrada");
		}

	}
	@Override
	public List<Personal> listar() {
		List<Personal> listado = new ArrayList<>();
		for (Personal personal : personalRepository.findAll()) {
			listado.add(personal);
		}
		return listado;
	}

	@Override
	public Personal buscarPersonal(Integer id) throws Exception {
		Optional<Personal> optPersonal =personalRepository.findById(id);
		if (optPersonal.isEmpty()) {
			throw new Exception("la persona no existe");
		}
		return optPersonal.get();
	}

	@Transactional
	@Override
	public void eliminarPersonal(Integer id) throws Exception {
		try {
			Personal personaBuscada  = this.buscarPersonal(id);
			personalRepository.delete(personaBuscada);
		} catch (Exception e) {
			throw new Exception("la persona no existe");
		}
	}

}
