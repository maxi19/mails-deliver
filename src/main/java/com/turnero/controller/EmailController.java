package com.turnero.controller;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;
import com.turnero.service.MailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/email")
public class EmailController {


    @Autowired
    MailServiceImp mailServiceImp;

    @PostMapping(value = "/envio/individual", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivo(@Valid @RequestBody Personal personal,@Valid @RequestBody ReciboSinIdentificar recibo) throws Exception{
        mailServiceImp.enviarReciboIndividual(personal,recibo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/varios", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(@Valid @RequestBody DocenteDto docenteDto) throws Exception{
        mailServiceImp.enviarVariosRecibos(docenteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
