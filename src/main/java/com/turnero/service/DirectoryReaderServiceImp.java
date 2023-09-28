package com.turnero.service;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.turnero.entity.Recibo;
import com.turnero.repository.ReciboRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;
import com.turnero.repository.PersonalRepository;
import com.turnero.repository.ReciboSinIdentificarRepository;
import com.turnero.repository.RegistroRepository;

@Service
public class DirectoryReaderServiceImp implements DirectoryReaderService {

	@Value("${config.path.recibos}")
	private String path;
	
    private static final Logger logger = LoggerFactory.getLogger(DirectoryReaderServiceImp.class);

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private PersonalRepository personalRepository;
	
	private Matcher matcher ;
	
	private Pattern pattern;
	
	@Autowired
	private ReciboSinIdentificarRepository recibosSinIdentificarRepository;

	@Autowired
	private ReciboRepository reciboRepository;



	
	@Override
	public Set<String> getFileName() throws Exception {

		Set<String> nombres = new HashSet<String>();
		
		File carpeta = new File(path);
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0) 
			throw new Exception("no hay archivos en la carpeta destino");
		//limpiamos la db de archivos de nombres
		recibosSinIdentificarRepository.deleteAll();
		for (int i=0; i< listado.length; i++) {
		        nombres.add(listado[i]);
		        ReciboSinIdentificar r = new  ReciboSinIdentificar();
		        r.setFileName(listado[i]);
		        recibosSinIdentificarRepository.save(r);
				;
		 }
		
		return nombres;
	}


	@Override
	public Set<String> machear() throws Exception {
	
		Map<String, String> mapa = new HashMap<>();
		
		//obtenemos la lista de nombres y su padron para obtener los archivos
		Iterable<Personal> personales = personalRepository.findAll();
		
		Iterable<ReciboSinIdentificar> recibosSinI = recibosSinIdentificarRepository.findAll();
	
		personales.forEach(x->{
			logger.info("el usuario {}  tiene los siguientes archivos ", x.getNombres().concat(",").concat(x.getApellidos()));
			recibosSinI.forEach(j->{
			this.pattern = Pattern.compile(x.getPatron());
			this.matcher = pattern.matcher(j.getFileName());
			if (matcher.find()) {
				logger.info((path.concat(" " + matcher.group())));
				Recibo recibo = new  Recibo();
				recibo.setPersonal(x);
				recibo.setFileName(j.getFileName());
				reciboRepository.save(recibo);

			}
			});
			
		});
		
		return null;
	}


}
