package com.turnero.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.turnero.component.UserComponent;
import com.turnero.dto.FileMessage;
import com.turnero.dto.FileModel;
import com.turnero.service.FileService;
import com.turnero.service.UserService;

@RestController
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class FileController {

    @Autowired
    private FileService fileService;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private UserService userService;

	private static final Logger log =  LoggerFactory.getLogger(FileController.class);

    private final static String FILE_UPLOADED_SUCCESSFULLY  = "Se subieron los archivos correctamente";

    private final static String FILE_ERROR = "Fallo al subir los archivos";

    @PostMapping("/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files , HttpServletRequest servletRequest) throws Exception{

		String username =userComponent.getUser(servletRequest);

		final String folderBase = userService.findByUserName(username).getFolderEntrada();

        try{
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file->{
                fileService.save(file,folderBase, username);
                fileNames.add(file.getOriginalFilename());
                log.info("se sube archivo {}",file.getOriginalFilename());
            });
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(FILE_UPLOADED_SUCCESSFULLY + fileNames));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(FILE_ERROR));
        }
    }





    @GetMapping(value = "/files")
    public ResponseEntity<List<FileModel>> getFiles(HttpServletRequest servletRequest) throws Exception{

    	String username =  userComponent.getUser(servletRequest);
    	final String folderBase = userService.findByUserName(username).getFolderEntrada();

        List<FileModel> fileInfos = fileService.loadAll(folderBase).map(path ->{
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }



    @GetMapping(value = "files/{filename:.+}", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<Resource> getFile(String filename) throws Exception{
        Resource file =  fileService.load(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"'+ file.getFileName() + '\"").body(file);

    }
    @GetMapping(value = "/delete/{filename:.+}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
    public ResponseEntity<FileMessage> delete(@PathVariable String filename) throws Exception{
        String message = null;
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK ).body(new FileMessage(message));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    }
