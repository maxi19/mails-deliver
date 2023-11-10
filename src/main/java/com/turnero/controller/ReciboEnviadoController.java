package com.turnero.controller;

import com.turnero.entity.ReciboEnviado;
import com.turnero.service.ReciboEnviadoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/recibo/enviado")
@CrossOrigin(origins = "http://localhost:4200")

public class ReciboEnviadoController {

    @Autowired
    private ReciboEnviadoService reciboEnviadoService;
    
	private static final Logger log =  LoggerFactory.getLogger(ReciboEnviadoController.class);

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<List<ReciboEnviado>> listarRecibos() throws Exception {
        List<ReciboEnviado> recibos= new ArrayList<>();
        for (ReciboEnviado reciboEnviado: reciboEnviadoService.listarRecibos()) {
            recibos.add(reciboEnviado);
        }
        return new ResponseEntity<>(recibos,HttpStatus.OK);
    }
    @PostMapping(value = "/eliminar/recibos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarRecibosEnviados(@Valid @RequestBody List<ReciboEnviado> reciboEnviados) throws Exception{
        reciboEnviadoService.eliminarVarios(reciboEnviados);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/eliminar/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarReciboEnviado(@PathVariable Integer id) throws Exception{
        reciboEnviadoService.eliminarIndividual(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/eliminar/todos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarTodos() throws Exception {
        reciboEnviadoService.eliminarTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
