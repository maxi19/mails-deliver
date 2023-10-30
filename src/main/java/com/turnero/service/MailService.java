package com.turnero.service;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;

public interface MailService {
    public void enviarSinMatch(Personal personal, ReciboSinIdentificar recibo);

    /*public void enviarRecibos(DocenteDto docenteDto);

    public void enviarRecibo(DocenteDto docenteDto, int idItem);*/
}
