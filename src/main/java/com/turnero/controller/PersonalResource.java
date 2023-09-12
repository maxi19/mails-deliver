package com.turnero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnero.entity.Personal;
import com.turnero.service.PersonalService;

@RestController
@RequestMapping(value = "/personal")
public class PersonalResource {
	
	@Autowired
	private PersonalService personalService;

    @PostMapping( value = "/registrar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE } )
    public ResponseEntity<Void> registerPersona(@Valid  @RequestBody Personal personal) throws  Exception{
    	String nombres = personal.getNombres().trim().toUpperCase();
    	String apellidos = personal.getApellidos().trim().toUpperCase();
    	
    	String padron = apellidos.concat(".+").concat(nombres).concat(".+.PDF") ;
    	if (personal.getPatron() ==  null) {
			personal.setPatron(padron);
		}
    	this.personalService.Add(personal);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
}
