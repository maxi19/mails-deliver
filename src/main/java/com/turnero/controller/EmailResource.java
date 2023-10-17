package com.turnero.controller;

import com.turnero.dto.DocenteDto;
import com.turnero.dto.MessageUser;
import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
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
import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {


    @Autowired
    MailServiceImp mailServiceImp;

    @PostMapping(value = "/envio/Individual", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivo(@Valid @RequestBody Personal personal, ReciboSinIdentificar recibo) throws Exception{
        mailServiceImp.sendSimpleMail(personal,recibo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/envio/varios", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Void> enviarArchivos(List<DocenteDto> docenteDtos) throws Exception{
        mailServiceImp.sendMultipartMail(docenteDtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
