package com.turnero.service;

import com.turnero.entity.ReciboEnviado;

import java.util.List;

public interface ReciboEnviadoService {

    public List<ReciboEnviado> listarRecibosEnvidados() throws Exception;

    public void eliminarVarios(List<ReciboEnviado> reciboEnviados) throws Exception;

    public void eliminarIndividual(Long id)throws Exception;

    public void eliminarTodos()throws Exception;
}
