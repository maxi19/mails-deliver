package com.turnero.controller;

import com.turnero.entity.ReciboEnviado;
import com.turnero.entity.ReciboIdentificado;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(value = "/reciboEnviado")
public class ReciboEnviadoController {
    @GetMapping(value = "/listarRecibosEnviado", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<ReciboEnviado> listarRecibosEnviados(){
        return null;
    }
    @PostMapping(value = "/eliminar/recibosEnviados", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarRecibosEnviados(List<ReciboEnviado> reciboEnviados){
        return null;
    }
    @PostMapping(value = "/eliminar/reciboEnviado/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarReciboEnviado(ReciboEnviado reciboEnviado){
        return null;
    }

    @PostMapping(value = "/eliminar/todosRecibosEnviado", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public ResponseEntity<Void> eliminarTodos(){
        return null;
    }

}
