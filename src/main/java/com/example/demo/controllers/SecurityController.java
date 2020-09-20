package com.example.demo.controllers;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.ProjectService;
import com.example.demo.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects/{id}")
    public ResponseEntity<Boolean> checkUserAccess (@PathVariable Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(securityService.userHasAccessToProject(projectService.getProject(id)));
    }

    @GetMapping("/projects/adminRole/{id}")
    public ResponseEntity<Boolean> checkIfUserIsAnAdmin (@PathVariable Long id) throws ObjectNotFoundException {
        return ResponseEntity.ok(securityService.userIsAnAdmin(projectService.getProject(id)));
    }
}
