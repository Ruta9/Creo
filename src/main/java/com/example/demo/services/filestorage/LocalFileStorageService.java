package com.example.demo.services.filestorage;

import com.example.demo.exceptions.FileStorageException;
import com.example.demo.utils.FileExtension;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class LocalFileStorageService implements FileStorageService{

    private final Path root = Paths.get("uploads");

    public LocalFileStorageService (){
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize folder for uploading files: " + root);
        }
    }

    @Override
    public String store(MultipartFile file, String filename) throws FileStorageException {
        try {
            Path destination = this.root.resolve(filename + FileExtension.getExtension(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toString();
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file: " + file.getOriginalFilename());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return this.root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public byte[] loadAsBytes(String filename) throws FileStorageException {
        try {
            return Files.readAllBytes(load(filename));
        } catch (IOException e) {
            throw new FileStorageException("Failed to read bytes from file: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        File file = new File(this.root.resolve(filename).toString());
        file.delete();
    }

    @Override
    public void deleteAll() {

    }
}
