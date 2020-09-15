package com.example.demo.controllers;

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

    @GetMapping("/projects/{id}")
    public ResponseEntity<Boolean> checkUserAccess (@PathVariable Long id) {
        return ResponseEntity.ok(securityService.userHasAccessToProject(id));
    }
}
