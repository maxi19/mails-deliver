package com.turnero.controller;

import com.turnero.entity.ReciboEnviado;
import com.turnero.service.ReciboEnviadoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/recibo/enviado")
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class ReciboEnviadoController {

    @Autowired
    private ReciboEnviadoService reciboEnviadoService;
    
	private static final Logger log =  LoggerFactory.getLogger(ReciboEnviadoController.class);

	//@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_DOCENTE','ROLE_SECRETARIA')")
    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<List<ReciboEnviado>> listarRecibos() throws Exception {
    	log.info("se solicita listado de recibos enviados");
        return new ResponseEntity<>(reciboEnviadoService.listarRecibos(),HttpStatus.OK);
    }
	
	//@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_DOCENTE','ROLE_SECRETARIA')")
    @PostMapping(value = "/eliminar/recibos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarRecibosEnviados(@Valid @RequestBody List<ReciboEnviado> reciboEnviados) throws Exception{
        reciboEnviadoService.eliminarVarios(reciboEnviados);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	//@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_DOCENTE','ROLE_SECRETARIA')")
    @GetMapping(value = "/eliminar/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarReciboEnviado(@PathVariable Integer id) throws Exception{
        reciboEnviadoService.eliminarIndividual(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	//@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_DOCENTE','ROLE_SECRETARIA')")
    @PostMapping(value = "/eliminar/todos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarTodos() throws Exception {
        reciboEnviadoService.eliminarTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
