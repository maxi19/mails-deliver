package com.turnero.controller;

import java.util.List;
import java.util.Set;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.ReciboSinIdentificar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.turnero.service.DirectoryReaderService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/msdeliver")
@CrossOrigin(origins = "http://localhost:4200")
public class DeliverResource {

	@Autowired
	private DirectoryReaderService directoryReaderService;	

	
	
	@GetMapping(value =  "/archivos/nombres" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<Set<ReciboSinIdentificar>>  listarArchivos() throws Exception {
		return new ResponseEntity<>(directoryReaderService.getFileName(),HttpStatus.OK);
	}
	
	
	
	@PostMapping(value =  "/archivos/machear" , produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<List<DocenteDto>>  machearArchivos(@Valid @RequestBody List<ReciboSinIdentificar> recibosSinIdentificar) throws Exception {
		return new ResponseEntity<>(directoryReaderService.machear(recibosSinIdentificar),HttpStatus.OK);
	}

	/*@PostMapping(value =  "/archivos/patron")
	public void   asdas(){
		directoryReaderService.patron();
	}*/
	
}
