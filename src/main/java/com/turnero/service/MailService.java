package com.turnero.service;

import com.turnero.dto.DocenteDto;
import com.turnero.dto.EnvioSinMatchDto;

public interface MailService {
    public void enviarSinMatch(EnvioSinMatchDto envioSinMatchDto);

    public void enviarRecibos(DocenteDto docenteDto);

    public void enviarRecibo(DocenteDto docenteDto, int idItem);
}
