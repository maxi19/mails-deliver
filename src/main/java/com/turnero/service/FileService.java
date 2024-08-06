package com.turnero.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void init();
    public void save(MultipartFile file, String folderBase, String usuario);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll(String folderBase);
    public String deleteFile(String filename);

}
