package com.turnero.manager;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.turnero.dto.SetCorreoDto;
import com.turnero.dto.FileMessage;
import com.turnero.dto.FileModel;
import com.turnero.dto.PersonalDto;
import com.turnero.exceptions.DeliverException;



public interface RecibosManager {
	public void procesarArchivosABandeja(HttpServletRequest servletRequest) throws Exception;
	
	public void procesarArchivosABandejaPorUsuario(HttpServletRequest servletRequest, PersonalDto personal) throws Exception;

	public List<SetCorreoDto> listarArchivosEnBandeja(HttpServletRequest servletRequest) throws Exception;
	
	public void listarArchivosProcesados() throws Exception;
	
	public void machearArchivosEnBandeja() throws Exception;

    public ResponseEntity<FileMessage> subirArchivos(MultipartFile[] files , HttpServletRequest servletRequest ) throws DeliverException;

    public ResponseEntity<List<FileModel>> obtenerArchivos(HttpServletRequest servletRequest) throws Exception;
    
    public ResponseEntity<Resource> obtenerArchivo(String filename) throws Exception;

	public ResponseEntity<FileMessage> delete(String filename) throws Exception;
	

}
