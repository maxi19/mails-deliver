package com.turnero.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.turnero.dto.SetCorreoDto;
import com.turnero.entity.Recibo;
import com.turnero.entity.User;
import com.turnero.enums.Estado;
import com.turnero.repository.ReciboRepository;

@Service
public class ReciboServiceImp implements ReciboService{

	@Autowired
	private ReciboRepository reciboRepository;

	@Autowired
	private UserService userService;

	@Value("${config.path.recibos}")
	private String path;

    private static final Logger logger = LoggerFactory.getLogger(ReciboServiceImp.class);


    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper getMappper() {
    	return this.modelMapper = new ModelMapper();
    }




    public ReciboServiceImp(ReciboRepository reciboRepository ) {
    	this.reciboRepository = reciboRepository;
    }



	@Override
	public void procesarRecibo(String strFilePath, Estado estado, User userConfigEmisor, String destinatario) throws Exception {
		File file = new File(path.concat(userConfigEmisor.getFolderEntrada().concat("\\").concat(strFilePath)));
		File fileDestino= new File(path.concat(userConfigEmisor.getFolderBandeja().concat("\\").concat(strFilePath)));
		FileUtils.copyFile(file, fileDestino);
		FileUtils.delete(file);
		Optional<Recibo> recibo = reciboRepository.findByNombre(strFilePath);
		recibo.get().setEstado(Estado.BANDEJA);
		recibo.get().setPath(fileDestino.getPath());
		User userDestinatario = this.userService.findByEmail(destinatario);
		recibo.get().setDestinatario(userDestinatario.getFirstName().concat(" ").concat(userDestinatario.getLastName()));
		recibo.get().setEmail(destinatario);
		this.reciboRepository.save(recibo.get());
	}

	@Override
	public List<SetCorreoDto> listarArchivosEnBandeja(String usuario) throws Exception {

		List<SetCorreoDto> emails = new ArrayList<>();

		 Optional<List<Recibo>> recibos = this.reciboRepository.findByEstadoAndUsuario(Estado.BANDEJA, usuario);
		 if (recibos.isPresent()) {
			 List<Recibo> recibosFiltrado;

			 //obtenemos todos los mail distintos de la coleccion
			 List<String> emailSinDuplicar = filtarEmailDuplicados(recibos);

			 //filtramos por email
			 filtrarListaPorEmail(emails, recibos, emailSinDuplicar);
		}

		return emails;
	}




	private void filtrarListaPorEmail(List<SetCorreoDto> emails, Optional<List<Recibo>> recibos,
			List<String> emailSinDuplicar) {
		emailSinDuplicar.stream().forEach(email ->{
			 List<Recibo> recibosFiltradosPorEmail  =  recibos.get()
					 									.stream()
					 									.filter(
					 										r -> r.getEmail().startsWith(email)
					 									).collect(Collectors.toList());

			 emails.add(new SetCorreoDto(1,email,"",recibosFiltradosPorEmail,null));
		 });
	}




	private List<String> filtarEmailDuplicados(Optional<List<Recibo>> recibos) {
		List<String> emailSinDuplicar = recibos.get()
				 					  .stream()
				 					  .map(item ->item.getEmail())
				 					  .distinct()
				 					  .collect(Collectors.toList());
		return emailSinDuplicar;
	}


	@Override
	public void procesarRecibosEntrantes(String pathOrigen, String pathDestino, Estado estado, String usuario) throws Exception {
		final File carpeta = new File(pathOrigen);
		final File padreCarpetaBandeja = new File(pathDestino);

		//es porque pasa los archivos a a bandeja
		if (estado.equals(Estado.PROCESADO)) {
			moverAbandejaYprocesar(estado, carpeta, padreCarpetaBandeja, usuario);
		}

	}


	private void moverAbandejaYprocesar(final Estado estado, final File carpeta, final File carpetaBandeja , final String usuario)
			throws IOException {
				FileUtils.copyDirectory(carpeta, carpetaBandeja);

				for (final File ficheroEntrada : carpeta.listFiles()) {

				logger.info("se movera archivo : {} , a directorio : {} , y pasara a estado estado {}",
				 ficheroEntrada, carpetaBandeja.getAbsolutePath(), estado);
				 FileUtils.delete(carpetaBandeja);

		       registrarRecibo(estado, ficheroEntrada.getAbsolutePath(), ficheroEntrada.getName(), usuario);
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



	@Override
	public Optional<Recibo> buscarArchivosPorNombre(String fileName) throws Exception {
		return this.reciboRepository.findByNombre(fileName);
	}




	@Override
	public Optional<List<Recibo>> buscarPorEmailEnBandeja(String email) throws Exception {
		return this.reciboRepository.findByEmailAndEstado(email, Estado.BANDEJA);
	}




	@Override
	public void registrarEnviado(List<Recibo> libros) throws Exception {
		libros.stream().forEach(libro ->{
			libro.setEstado(Estado.ENVIADO);
			libro.setFecha(LocalDateTime.now());
			this.reciboRepository.save(libro);
		});
	}


}
