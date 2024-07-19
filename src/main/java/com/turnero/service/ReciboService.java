package com.turnero.service;

import java.io.File;
import java.util.List;

import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.enums.Estado;

public interface ReciboService {

	public void procesarArchivo(File from, Estado estado, User user) throws Exception;

	public List<Recibo> listarArchivosEnBandeja() throws Exception;

	public void procesarRecibosEntrantes(String pathOrigen, String pathDestino, Estado estado, String usuario ) throws Exception;

	public void machearArchivosProfesores (List<Personal> personal)  throws Exception ;
	
	//public void registrarRecibo(final Estado estado ,final String path,final  String fileName, final String usuario );
}
