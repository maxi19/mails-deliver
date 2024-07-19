package com.turnero.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.enums.Estado;
import com.turnero.repository.ReciboRepository;

@Service
public class ReciboServiceImp implements ReciboService{

	@Autowired
	private ReciboRepository reciboRepository;
	
    private static final Logger logger = LoggerFactory.getLogger(ReciboServiceImp.class);
   
      
    public ReciboServiceImp(ReciboRepository reciboRepository ) {
    	this.reciboRepository = reciboRepository;
    }
    
 
  
	@Override
	public void procesarArchivo(File from, Estado estado, User user) throws Exception {
		try {
			
			//validamos si ya fue propcesado anterioirmente
			
			if (!reciboRepository.findByNombre(from.getName()).isEmpty() &&
					reciboRepository.findByNombre(from.getName()).get().getEstado().equals(Estado.ENVIADO) )
				throw new Exception("El archivo ya fue enviado" );
				
			/*
			String nuevoPath =user.getPath().concat(from.getPath());
				
			from.renameTo(new File(nuevoPath));
			from.delete();
	
			Recibo nuevoRecibo = new Recibo();
			nuevoRecibo.setEstado(estado);
			nuevoRecibo.setNombre(nuevoPath);
			//nuevoRecibo.setUser(user);
			nuevoRecibo.setUsuario(user.getUsername());
			reciboRepository.save(nuevoRecibo);
		*/	
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public List<Recibo> listarArchivosEnBandeja() throws Exception {
		return null;
	}


	@Override
	public void procesarRecibosEntrantes(String pathOrigen, String pathDestino, Estado estado, String usuario) throws Exception {
		final File carpeta = new File(pathOrigen);
		final File padreCarpetaBandeja = new File(pathDestino);

		//es porque pasa los archivos a a bandeja
		if (estado.equals(Estado.PROCESADO))
			moverAbandejaYprocesar(estado, carpeta, padreCarpetaBandeja, usuario);
			
	}


	private void moverAbandejaYprocesar(final Estado estado, final File carpeta, final File carpetaBandeja , final String usuario)
			throws IOException {
				FileUtils.copyDirectory(carpeta, carpetaBandeja);
				
				for (final File ficheroEntrada : carpeta.listFiles()) {
	
				logger.info("se movera archivo : {} , a directorio : {} , y pasara a estado estado {}", 
						ficheroEntrada, carpetaBandeja.getAbsolutePath(), estado);

				eliminarArchivo(ficheroEntrada);

		       registrarRecibo(estado, ficheroEntrada.getAbsolutePath(), ficheroEntrada.getName(), usuario);
		 }
	}
	


	@Override
	public void machearArchivosProfesores(List<Personal> personal) throws Exception {
		// TODO Auto-generated method stub
		
		
	}
	private void eliminarArchivo(File file) {
		  try {
		        file.delete();
			} catch (Exception e) {
				// TODO: registrar el archivos que no se movio
			}
	}

    public void registrarRecibo(final Estado estado , final String path,final  String fileName, final String usuario ) {
    	Recibo recibo = new Recibo();
        recibo.setEstado(estado);
        recibo.setFecha(LocalDateTime.now());
        recibo.setNombre(fileName);
        recibo.setPath(path);
        recibo.setUsuario(usuario);
        reciboRepository.save(recibo);
    }
	
	
}
