package com.turnero.controller;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;
import com.turnero.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/email")
public class EmailController {


    @Autowired
    MailService mailService;

    @PostMapping(value = "/envio/sinMatch", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarSinMatch(@Valid @RequestBody Personal personal,@Valid @RequestBody ReciboSinIdentificar recibo) throws Exception{
        mailService.enviarSinMatch(personal,recibo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* @PostMapping(value = "/envio/varios", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(@Valid @RequestBody DocenteDto docenteDto) throws Exception{
        mailService.enviarRecibos(docenteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/individual/{numRecibo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> enviarIndividual(@Valid @RequestBody DocenteDto docenteDto, @PathVariable int numRecibo){
        mailService.enviarRecibo(docenteDto, numRecibo);
        return  new ResponseEntity<>(HttpStatus.OK);
    }*/
}
