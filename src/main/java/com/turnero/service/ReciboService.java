package com.turnero.service;

import java.util.List;
import java.util.Optional;

import com.turnero.dto.SetCorreoDto;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.enums.Estado;

public interface ReciboService {

	public void procesarRecibo(String pathFile, Estado estado, User user , String destinatario) throws Exception;

	public List<SetCorreoDto> listarArchivosEnBandeja(String usuario) throws Exception;

	public void procesarRecibosEntrantes(String pathOrigen, String pathDestino, Estado estado, String usuario ) throws Exception;

	public Optional<Recibo> buscarArchivosPorNombre (String fileName)  throws Exception;
	
	public Optional<List<Recibo>> buscarPorEmailEnBandeja(String email) throws Exception;
	
	public void registrarEnviado(List<Recibo> libro) throws Exception;
	
	}
