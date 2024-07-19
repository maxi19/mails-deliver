package com.turnero.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.turnero.dto.FileMessage;
import com.turnero.dto.FileModel;
import com.turnero.exceptions.DeliverException;
import com.turnero.manager.RecibosManager;


@RestController
@RequestMapping(value = "/recibos")
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class RecibosController {

	 	@Autowired
	    private RecibosManager recibosManager;
	    
		private static final Logger log =  LoggerFactory.getLogger(RecibosController.class);


	    @GetMapping(value = "/procesarArchivos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	    public ResponseEntity<Void> procesar( HttpServletRequest servletRequest ) throws Exception {
	    	recibosManager.procesarArchivosABandeja(servletRequest);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    

	    @GetMapping(value = "/machear", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	    public ResponseEntity<Void> machear() throws Exception {
	    	recibosManager.machearArchivosEnBandeja();
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    @PostMapping("/upload")
	    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files , HttpServletRequest servletRequest) throws DeliverException{
	    	return recibosManager.subirArchivos(files, servletRequest);
	    }
	   
	    @GetMapping(value = "/files")
	    public ResponseEntity<List<FileModel>> getFiles(HttpServletRequest servletRequest) throws Exception{
	    	return recibosManager.obtenerArchivos(servletRequest);
	    }
	   
	    
	    @GetMapping(value = "files/{filename:.+}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	    public ResponseEntity<Resource> getFile(String filename) throws Exception{
	    	return recibosManager.obtenerArchivo(filename);
	    }

	    
	    @GetMapping(value = "/delete/{filename:.+}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	    public ResponseEntity<FileMessage> delete(@PathVariable String filename) throws Exception{
	    	return recibosManager.delete(filename);
	    }

	    
}
