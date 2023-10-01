package com.turnero.controller;

import com.turnero.dto.MessageUser;
import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
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
import java.io.File;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {


    @Autowired
    MailServiceImp mailServiceImp;

    @PostMapping(value = "/envio/Individual", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivo(@Valid @RequestBody Recibo recibo, Personal personal, File file, MessageUser messageUser) throws Exception{
        mailServiceImp.sendSimpleMail(recibo, personal, file, messageUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/multiples", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(Recibo recibo) throws Exception{
        mailServiceImp.sendMultipartMail();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
