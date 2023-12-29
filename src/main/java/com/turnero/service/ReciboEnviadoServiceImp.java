package com.turnero.service;

import com.turnero.entity.ReciboEnviado;
import com.turnero.repository.ReciboEnviadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ReciboEnviadoServiceImp implements ReciboEnviadoService{


    @Autowired
    ReciboEnviadoRepository reciboEnviadoRepository;
    @Override
    public List<ReciboEnviado> listarRecibos(){
            List<ReciboEnviado> recibos = new ArrayList<>();
            reciboEnviadoRepository.findAll().forEach(recibos::add);
            return recibos;
    }

    @Override
    public void eliminarVarios(List<ReciboEnviado> reciboEnviados) {
        for (ReciboEnviado reciboEnviado: reciboEnviados) {
            reciboEnviadoRepository.delete(reciboEnviado);
        }
    }

    @Override
    public void eliminarIndividual(Integer id) throws Exception{
        try {
            ReciboEnviado reciboBuscado  = this.buscarRecibo(id);
            reciboEnviadoRepository.delete(reciboBuscado);
        } catch (Exception e) {
            throw new Exception("la persona no existe");
        }
    }

    @Override
    public void eliminarTodos() {
        try {
            reciboEnviadoRepository.deleteAll();
        }catch (Exception e){

        }

    }

    @Override
    public ReciboEnviado buscarRecibo(Integer id) throws Exception {
        Optional<ReciboEnviado> optRecibo = reciboEnviadoRepository.findById(id);
        if (optRecibo.isEmpty()) {
            throw new Exception("la persona no existe");
        }
        return optRecibo.get();
    }
    
    
    
}
