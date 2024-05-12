package com.turnero.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.enums.Estado;
import com.turnero.repository.ReciboRepository;

@Service
public class ReciboServiceImp implements ReciboService{

	@Autowired
	private ReciboRepository reciboRepository;
	
	
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
	
	
	
	
	
	
}
