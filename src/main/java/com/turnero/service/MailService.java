package com.turnero.service;

import com.turnero.dto.DocenteDto;

public interface MailService {

    public void enviarRecibos(DocenteDto docenteDto);

    public void enviarRecibo(DocenteDto docenteDto, int idItem);
}
