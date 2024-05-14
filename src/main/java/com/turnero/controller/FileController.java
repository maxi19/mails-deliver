package com.turnero.controller;

import com.turnero.dto.FileMessage;
import com.turnero.dto.FileModel;
import com.turnero.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "${cross.origin}", allowCredentials = "true")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files){
        String message = "";
        try{
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file->{
                fileService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch (Exception e){
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }





    @GetMapping(value = "/files")
    public ResponseEntity<List<FileModel>> getFiles() throws Exception{
        List<FileModel> fileInfos = fileService.loadAll().map(path ->{
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class,"getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping(value = "files/{filename:.+}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE })
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
