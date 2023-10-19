package com.turnero.service;

import com.turnero.entity.Operacion;
import com.turnero.entity.Personal;
import com.turnero.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
@Service
public class OperacionesServiceImp implements OperacionService{

    @Autowired
    private OperacionRepository operacionRepository;
    @Override
    public List<Operacion> listar() throws Exception {

        List<Operacion> listado = new ArrayList<Operacion>();
        for (Operacion operacion : operacionRepository.findAll()) {
            listado.add(operacion);
        }
        return listado;
    }


}
