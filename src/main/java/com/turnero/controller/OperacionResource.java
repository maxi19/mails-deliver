package com.turnero.controller;

import com.turnero.entity.Operacion;
import com.turnero.service.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/operacion")
public class OperacionResource {

    @Autowired
    private OperacionService operacionService;


    @GetMapping(value = "/listar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<List<Operacion>>listar()throws Exception{
        List<Operacion> operacion = new ArrayList<>();

        for (Operacion operacionEntity: operacionService.listar()) {
            operacion.add(operacionEntity);
        }
        return new ResponseEntity<>(operacion, HttpStatus.OK);
    }
}
