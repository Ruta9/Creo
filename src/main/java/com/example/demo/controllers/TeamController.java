package com.example.demo.controllers;

import com.example.demo.DTOs.TeamMember;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{id}")
    public ResponseEntity<List<TeamMember>> getTeamForProject (@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
        return ResponseEntity.ok(projectService.getProjectTeamSecured(id));
    }
}
