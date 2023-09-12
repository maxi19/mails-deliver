package com.turnero.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnero.service.DirectoryReaderService;


@RestController
@RequestMapping(value = "/msdeliver")
public class DeliverResource {

	@Autowired
	private DirectoryReaderService directoryReaderService;	

	
	
	@GetMapping(value =  "/archivos/nombres" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Set<String>>  listarArchivos() throws Exception {
		return new ResponseEntity<>(directoryReaderService.getFileName(),HttpStatus.OK);
	}
	
	
	
	@GetMapping(value =  "/archivos/machear" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Void>  machearArchivos() throws Exception {
		directoryReaderService.machear();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
