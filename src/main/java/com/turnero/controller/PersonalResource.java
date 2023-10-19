package com.turnero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.turnero.entity.Personal;
import com.turnero.service.PersonalService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/personal")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalResource {
	
	@Autowired
	private PersonalService personalService;

    @PostMapping( value = "/registrar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE } )
    public ResponseEntity<Void> registerPersona(@Valid  @RequestBody Personal personal) throws  Exception{
    	String nombres = personal.getNombres().replaceAll(" ", "").toUpperCase();
		String apellidos = personal.getApellidos().replaceAll(" ", "").toUpperCase();
    	
    	String patron = apellidos.concat(",").concat(nombres).concat("_").concat(".+").concat(".pdf") ;
    	if (personal.getPatron() ==  null) {
			personal.setPatron(patron);
		}
    	this.personalService.Add(personal);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
	@PostMapping(value =  "/editarPersonal" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Personal>  editarPersonal(@RequestBody Personal personal) throws Exception {
		personalService.Add(personal);
		return new ResponseEntity<Personal>(personal, HttpStatus.OK);
	}


	@GetMapping(value =  "/eliminarPersonal/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Void>  eliminar(@PathVariable UUID id  ) throws Exception {
		personalService.eliminarPersonal(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping(value =  "/buscarPersonal/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Personal>  prueba2(@PathVariable UUID id  ) throws Exception {
		Personal p = personalService.buscarPersonal(id);
		return new ResponseEntity<Personal>(p,HttpStatus.OK);
	}


	@GetMapping(value =  "/listar" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<List<Personal>>  personas() throws Exception {
		List<Personal> personal = new ArrayList<Personal>();
		for (Personal personaEntity : personalService.listar()) {
			personal.add(personaEntity);
		}
		return new ResponseEntity<>(personal, HttpStatus.OK);
	}
	
}
