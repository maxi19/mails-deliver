package com.turnero.service;

import java.io.File;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.turnero.dto.DocenteDto;
import com.turnero.dto.ItemDto;
import com.turnero.entity.ReciboEnviado;
import com.turnero.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;

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
	private ReciboEnviadoRepository reciboEnviadoRepository;

	@Autowired
	private OperacionRepository operacionRepository;



	@Override
	public void patron() {
		List<Personal> personales = (List<Personal>) personalRepository.findAll();

		for (Personal personal : personales) {
			String nombres = personal.getNombres().replaceAll(" ", "").toUpperCase();
			String apellidos = personal.getApellidos().replaceAll(" ", "").toUpperCase();
			String patron = apellidos.concat(",").concat(nombres).concat("_").concat(".+").concat(".pdf") ;
			personal.setPatron(patron);

			personalRepository.save(personal);
		}
	}

	@Override
	public void crearPDF(){

		File directorio = new File(path);
		String palabraRandom[] ={"recibo1", "recibo2"};
		for (Personal personal: personalRepository.findAll()) {

			for (int i = 0; i < 2; i++){

				File pdf = new File(directorio, "/".concat(personal.getApellidos().concat(",".concat(personal.getNombres().concat("_".concat(palabraRandom[i]).concat(".pdf"))))));
				try {
					pdf.createNewFile();

				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

		}



	}

	@Override
	public Set<ReciboSinIdentificar> getFileName() throws Exception {

		Set<ReciboSinIdentificar> recibosSinIdentificar = new HashSet<ReciboSinIdentificar>();

		File carpeta = new File(path);
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0)
			throw new Exception("no hay archivos en la carpeta destino");
		//limpiamos la db de archivos de nombres
		recibosSinIdentificarRepository.deleteAll();
		for (int i=0; i< listado.length; i++) {
				ReciboSinIdentificar r = new  ReciboSinIdentificar();
				r.setFileName(listado[i]);
				recibosSinIdentificarRepository.save(r);

		}
		recibosSinIdentificarRepository.findAll().forEach(x->{
			recibosSinIdentificar.add(x);
		});

		return recibosSinIdentificar;
	}


	@Override
	public List<DocenteDto> machear(List<ReciboSinIdentificar> recibosSin) throws Exception {

		Map<String, String> mapa = new HashMap<>();

		//obtenemos la lista de nombres y su padron para obtener los archivos
		List<DocenteDto> docentesDto = new ArrayList<DocenteDto>();

		//Iterable<ReciboSinIdentificar> recibos = recibosSinIdentificarRepository.findAll();
		List<ItemDto> items = null;
		for (Personal personal : personalRepository.findAll()) {
			logger.info("el usuario {} , tiene los siguientes archivos ", personal.getNombres().concat(",").concat(personal.getApellidos()));
			DocenteDto docente = new DocenteDto();
			docente.setNombres(personal.getNombres());
			docente.setApellidos(personal.getApellidos());
			docente.setEmail(personal.getEmail());
			ItemDto item = null;
			items = new ArrayList<ItemDto>();
			for (ReciboSinIdentificar reciboSinIdentificar : recibosSin) {
				this.pattern = Pattern.compile(personal.getPatron());
				this.matcher = pattern.matcher(reciboSinIdentificar.getFileName().replaceAll(" ", ""));
				while (matcher.find()) {
					boolean enviado =false;
					for (ReciboEnviado reciboEnviados:reciboEnviadoRepository.findAll()) {
						if(reciboEnviados.getFileName() !=reciboSinIdentificar.getFileName()){
							enviado = true;
							break;
						}
					}
					if(!enviado){
						logger.info(path.concat(matcher.group()));
						item = new ItemDto();
						item.setArchivo(reciboSinIdentificar.getFileName());
						item.setEnviado(Boolean.FALSE);
						items.add(item);
					}
				}
			}
			docente.setItemDto(items);
			docentesDto.add(docente);
		}

		return docentesDto;
	}


}
