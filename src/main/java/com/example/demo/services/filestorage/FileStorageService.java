package com.example.demo.services.filestorage;

import com.example.demo.exceptions.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    String store (MultipartFile file, String filename) throws FileStorageException;
    Stream<Path> loadAll();
    Path load(String filename);
    byte[] loadAsBytes(String filename) throws FileStorageException;
    Resource loadAsResource(String filename);
    void delete(String filename);
    void deleteAll();

}
