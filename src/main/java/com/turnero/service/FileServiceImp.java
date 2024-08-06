package com.turnero.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.turnero.entity.Recibo;
import com.turnero.enums.Estado;
import com.turnero.repository.ReciboRepository;

@Service
public class FileServiceImp implements  FileService {


	@Value("${root.folder}")
	private String rootFolder;

	private Path root ;

    private static final Logger log =  LoggerFactory.getLogger(FileServiceImp.class);


	@Autowired
	private ReciboRepository reciboRepository;


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
    public void save(MultipartFile file , String folderBase, String usuario )  {
        try {
        	this.root = Paths.get(rootFolder.concat(folderBase));
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            this.registrarRecibo(Estado.NUEVO,  this.root.resolve(file.getOriginalFilename()).toString() , file.getOriginalFilename().toString(), usuario);

            log.info("se agrego a bandeja el archivio {} {} ", file.getName(), file.getOriginalFilename());

        } catch (IOException e) {
            throw  new RuntimeException("no se pudo guardar el archivo");
        } catch (Exception e) {
            e.printStackTrace();
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
    public Stream<Path> loadAll(String folderBase) {
        try {
        	this.root = Paths.get(rootFolder.concat(folderBase));
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException  | RuntimeException x)  {
            throw new RuntimeException("no se pudo cargar los archivos");
        }


    }

    @Override
    public String deleteFile(String filename) {
        try {
            //eliminamos el archivo en carpeta base
        	Boolean delete =  Files.deleteIfExists(this.root.resolve(filename));

        	//eliminamos el archivo en base
            Optional<Recibo> reciboOpt = reciboRepository.findByNombre(filename);
            reciboRepository.delete(reciboOpt.get());
            return "borrado";
        } catch (IOException e) {
            e.printStackTrace();
            return "error borrando archivos";
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
