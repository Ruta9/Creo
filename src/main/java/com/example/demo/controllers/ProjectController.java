package com.example.demo.controllers;

import com.example.demo.DTOs.ProjectCreateForm;
import com.example.demo.DTOs.ProjectGeneralInformation;
import com.example.demo.DTOs.ProjectGeneralInformationForm;
import com.example.demo.DTOs.UserProject;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.FileStorageException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.exceptions.UnsupportedFileFormatException;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/userProjects")
    public List<UserProject> getUserProjects() throws ObjectNotFoundException {
        return projectService.getUserProjects();
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<String> getProjectName(@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
        return ResponseEntity.ok(projectService.getProjectNameSecured(id));
    }

    @GetMapping("/general/{id}")
    public ResponseEntity<ProjectGeneralInformation> getDefaultProjectInformation(@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
            return ResponseEntity.ok(projectService.getProjectGeneralInfoSecured(id));
    }

    @PostMapping
    public ResponseEntity createProject(@Valid @RequestBody ProjectCreateForm projectCreateForm)
            throws ObjectNotFoundException {
            projectService.createProject(projectCreateForm);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateProject(
            @Valid @RequestPart("form") ProjectGeneralInformation projectGeneralInformation,
            @RequestPart(value = "file", required = false) MultipartFile multipartFile)
            throws UnsupportedFileFormatException, ObjectNotFoundException, FileStorageException {
        projectService.updateProject(new ProjectGeneralInformationForm(projectGeneralInformation, multipartFile));
        return ResponseEntity.ok().build();
    }

}
