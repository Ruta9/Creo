package com.example.demo.controllers;

import com.example.demo.DTOs.ProjectCreateDTO;
import com.example.demo.DTOs.UserProjectDTO;
import com.example.demo.services.ProjectService;
import com.example.demo.utils.UUIDStringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/userProjects")
    public List<UserProjectDTO> getUserProjects() {
        return projectService.getUserProjects();
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<String> getProjectName(@PathVariable Long id) {
        String name = projectService.getProjectNameSecured(id);
        if (name != null){
            return ResponseEntity.ok(name);
        }
        else return new ResponseEntity(new AbstractMap.SimpleEntry<>("error","Project does not exist or the user does not have the required access rights to see it"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity createProject(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
            projectService.createProject(projectCreateDTO);
            return new ResponseEntity(HttpStatus.CREATED);
    }

}
