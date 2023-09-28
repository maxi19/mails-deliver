package com.turnero.controller;

import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
import com.turnero.service.MailService;
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
public class EmailResource {


    @Autowired
    MailService mailService;

    @PostMapping(value = "/envio/Individual", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivo(@Valid @RequestBody Recibo recibo) throws Exception{
        mailService.sendSimpleMail(recibo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/multiples", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(Recibo recibo) throws Exception{
        mailService.sendMultipartMail();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
