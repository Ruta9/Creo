package com.example.demo.controllers;

import com.example.demo.DTOs.ProjectCreateDTO;
import com.example.demo.DTOs.roles.TeamRoleDTO;
import com.example.demo.DTOs.UserRoleDTO;
import com.example.demo.enums.Role;
import com.example.demo.services.ProjectRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    ProjectRolesService projectRolesService;

    @GetMapping("/project/{id}/current")
    public ResponseEntity<List<UserRoleDTO>> getCurrentUserRoles (@PathVariable Long id) {
        List<UserRoleDTO> userRoles = projectRolesService.getUserRolesForProject(id);
        if (userRoles == null) return new ResponseEntity(new AbstractMap.SimpleEntry<>("error","Project does not exist or the user does not have the required access rights to see it"), HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/project/{id}/team")
    public ResponseEntity<List<TeamRoleDTO>> getTeamRoles (@PathVariable Long id) {
        List<TeamRoleDTO> teamRoles = projectRolesService.getTeamRolesForProject(id);
        if (teamRoles == null) return new ResponseEntity(new AbstractMap.SimpleEntry<>("error","Project does not exist or the user does not have the required access rights to see it"), HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(teamRoles);
    }

    @PutMapping("/project/{id}/team/{role}")
    public ResponseEntity<String> updateProjectRoleForUsers (@PathVariable Long id, @PathVariable Role role, @Valid @RequestBody TeamRoleDTO teamRoleDTO) {
        String message = projectRolesService.updateProjectRole(id, role, teamRoleDTO);
        if (message == null) return new ResponseEntity(HttpStatus.OK);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
