package com.turnero.service;

import com.turnero.entity.ReciboEnviado;
import com.turnero.repository.ReciboEnviadoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RecibosEnviadoServiceImp implements ReciboEnviadoService{


    @Autowired
    ReciboEnviadoRepository reciboEnviadoRepository;
    @Override
    public List<ReciboEnviado> listarRecibosEnvidados() {
        List<ReciboEnviado> recibos = new ArrayList<>();

        for (ReciboEnviado reciboEnviado: reciboEnviadoRepository.findAll()) {
            recibos.add(reciboEnviado);
        }
        return recibos;
    }

    @Override
    public void eliminarVarios(List<ReciboEnviado> reciboEnviados) {

    }

    @Override
    public void eliminarIndividual(Long id) {
        reciboEnviadoRepository.findById(id);
    }

    @Override
    public void eliminarTodos() {
        try {
            reciboEnviadoRepository.deleteAll();
        }catch (Exception e){

        }

    }
}
