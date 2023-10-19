package com.turnero.service;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.Operacion;
import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;

import java.util.List;
import java.util.Set;

public interface DirectoryReaderService {

	public Set<ReciboSinIdentificar> getFileName() throws Exception;


	public List<DocenteDto> machear(List<ReciboSinIdentificar> reciboSinIdentificarse) throws Exception;

	public void patron();

}
