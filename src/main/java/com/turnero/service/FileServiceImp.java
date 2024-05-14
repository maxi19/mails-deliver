package com.turnero.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileServiceImp implements  FileService {

    private final Path root = Paths.get("upload");

    @Override
    public void init() {
    try {
        Files.createDirectory(root);
    }catch (IOException e )  {
        e.printStackTrace();
    }
        throw new RuntimeException("nose pudo iniciar");
    }

    @Override
    public void save(MultipartFile file)  {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw  new RuntimeException("no se pudo guardar el archivo");
        }
    }

    @Override
    public Resource load(String filename) {
        Path file  = root.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return  resource;
            }else{
                throw  new RuntimeException("no se pudo leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("errpr" +e.getMessage());
        }


    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException  | RuntimeException x)  {
            throw  new RuntimeException("no se pudo cargar los archivos");
        }


    }

    @Override
    public String deleteFile(String filename) {

        try {
            Boolean delete =  Files.deleteIfExists(this.root.resolve(filename));
            return "borrado";
        } catch (IOException e) {
            e.printStackTrace();
            return "error borrando archivos";
        }
    }
}
