package com.turnero.service;

import com.turnero.entity.Operacion;

import java.util.List;

public interface OperacionService {
    public List<Operacion> listar()throws Exception;
}
