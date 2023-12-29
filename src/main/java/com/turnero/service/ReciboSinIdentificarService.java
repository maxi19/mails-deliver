package com.turnero.service;

import com.turnero.dto.DocenteDto;
import com.turnero.entity.ReciboSinIdentificar;

import java.util.List;
import java.util.Set;

public interface ReciboSinIdentificarService {

	public Set<ReciboSinIdentificar> leerArchivos() throws Exception;


	public List<DocenteDto> machear(List<ReciboSinIdentificar> reciboSinIdentificarse) throws Exception;

	public void patron();

	public void crearPDF() ;

	//public void moverRecibo(  ) throws Exception;


}
