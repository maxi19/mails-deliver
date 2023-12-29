package com.turnero.service;

import com.turnero.entity.ReciboEnviado;

import java.util.List;

public interface ReciboEnviadoService {

    public List<ReciboEnviado> listarRecibos();

    public void eliminarVarios(List<ReciboEnviado> reciboEnviados) throws Exception;

    public void eliminarIndividual(Integer id)throws Exception;

    public void eliminarTodos()throws Exception;

    public ReciboEnviado buscarRecibo(Integer id)throws Exception;
    
    
    
}
