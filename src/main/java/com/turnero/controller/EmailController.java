package com.turnero.controller;


import com.turnero.component.UserComponent;
import com.turnero.dto.Enviables;
import com.turnero.manager.EmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/email")
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class EmailController {

	@Autowired
	private EmailManager emailManager;

    @Autowired
    private UserComponent userComponent;

    @PostMapping(value = "/enviar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(@Valid @RequestBody Enviables enviables , HttpServletRequest servletRequest) throws Exception{
    
    	String userName = userComponent.getUser(servletRequest);
		
    	emailManager.enviarEmail(enviables,userName );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    
    /*
    @PostMapping(value = "/envio/varios", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(@Valid @RequestBody DocenteDto docenteDto) throws Exception{
        mailService.enviarRecibos(docenteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/individual/{numRecibo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> enviarIndividual(@Valid @RequestBody DocenteDto docenteDto, @PathVariable int numRecibo){
        mailService.enviarRecibo(docenteDto, numRecibo);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    */

}
