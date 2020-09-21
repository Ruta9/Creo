package com.example.demo.controllers;

import com.example.demo.enums.MediaTypes;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.FileStorageException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.ProjectService;
import com.example.demo.utils.FileExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
@RequestMapping("/uploads")
public class FilesController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/image/{id}")
    public ResponseEntity getProjectImage (@PathVariable Long id)
            throws FileStorageException, ObjectNotFoundException, AccessForbiddenException {

        AbstractMap.Entry<String, byte[]> image = projectService.getProjectImageSecured(id);
        return ResponseEntity
                .ok()
                .contentType(FileExtension.getMediaType(image.getKey()))
                .body(image.getValue());
    }
}
