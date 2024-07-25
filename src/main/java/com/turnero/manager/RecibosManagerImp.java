package com.turnero.manager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.turnero.component.UserComponent;
import com.turnero.controller.FileController;
import com.turnero.controller.SessionTool;
import com.turnero.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.turnero.dto.SetCorreoDto;
import com.turnero.dto.FileMessage;
import com.turnero.dto.FileModel;
import com.turnero.dto.PersonalDto;
import com.turnero.enums.Estado;
import com.turnero.exceptions.DeliverException;
import com.turnero.redis.SessionDao;
import com.turnero.service.FileService;
import com.turnero.service.ReciboService;
import com.turnero.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


@Service
public class RecibosManagerImp implements RecibosManager {

    private static final Logger logger = LoggerFactory.getLogger(RecibosManagerImp.class);

	@Autowired
	private ReciboService reciboService;

	@Value("${root.folder}")
	private String rootFolder;

	@Autowired
	SessionTool sessionTool;

    @Autowired
    private FileService fileService;
	
    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserComponent userComponent;
    
    private final static String FILE_UPLOADED_SUCCESSFULLY  = "Se subieron los archivos correctamente";
    
    private final static String FILE_ERROR = "Fallo al subir los archivos";
    
	@Override
	public void procesarArchivosABandeja(HttpServletRequest servletRequest) throws Exception {
		logger.info("Se procedera a pasar los archivos a la bandeja");
		
		String userName = userComponent.getUser(servletRequest);
	  	User user =  userService.findByUserName(userName);
	  	 
		reciboService.procesarRecibosEntrantes(rootFolder.concat(user.getFolderEntrada()),
				rootFolder.concat(user.getFolderBandeja()), Estado.PROCESADO, user.getUsername());
	}

	@Override
	public void procesarArchivosABandejaPorUsuario(HttpServletRequest servletRequest, PersonalDto personal)
			throws Exception {
		String userName = userComponent.getUser(servletRequest);
	  	User userRemitente =  userService.findByUserName(userName);
	  	String destinatario = personal.getEmail();
	  	personal.getFileItems().forEach(file ->{
			try {
				reciboService.procesarRecibo(file.getName(), Estado.BANDEJA, userRemitente , destinatario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	  		
	  	});
		
	}

	@Override
	public void machearArchivosEnBandeja() throws Exception {
		HttpServletRequest servletRequest;

		
	}
	
	@Override
	public List<SetCorreoDto> listarArchivosEnBandeja( HttpServletRequest servletRequest) throws Exception {
		String user = userComponent.getUser(servletRequest);
		logger.info("Se procedera a machear archiovos de bandeja");
		return reciboService.listarArchivosEnBandeja(user);
	}
	
	@Override
	public void listarArchivosProcesados() throws Exception {
		
	}

	@Override
	public ResponseEntity<FileMessage> subirArchivos(MultipartFile[] files, HttpServletRequest servletRequest) throws DeliverException {
		
		String userName = userComponent.getUser(servletRequest);
		String path = sessionDao.getOneSession(userName).getFolderIncome();
		
        try{
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file->{
                fileService.save(file , path, userName );
                fileNames.add(file.getOriginalFilename());
                
            });
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(FILE_UPLOADED_SUCCESSFULLY + fileNames));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(FILE_ERROR));
        }
		
	}

	@Override
	public ResponseEntity<Resource> obtenerArchivo(String filename) throws Exception {
	    Resource file =  fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"'+ file.getFileName() + '\"").body(file);
	}

	public ResponseEntity<FileMessage> delete(String filename) throws Exception{
		String message = null;
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK ).body(new FileMessage(message));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
	 }

	@Override
	public ResponseEntity<List<FileModel>> obtenerArchivos(HttpServletRequest servletRequest) throws Exception {
		
		String userName = userComponent.getUser(servletRequest);
		String basePath = sessionDao.getOneSession(userName).getFolderIncome();
		
		List<FileModel> fileInfos = fileService.loadAll(basePath).map(path ->{
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}




}
