package com.example.demo.controllers;

import com.example.demo.DTOs.roles.ProjectRoleDTO;
import com.example.demo.DTOs.roles.UserRole;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
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
    public ResponseEntity<List<UserRole>> getCurrentUserRoles (@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
            return ResponseEntity.ok(projectRolesService.getUserRolesForProjectSecured(id));
    }

    @GetMapping("/project/{id}/team")
    public ResponseEntity<List<ProjectRoleDTO>> getTeamRoles (@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
        return ResponseEntity.ok(projectRolesService.getTeamRolesForProjectSecured(id, false));
    }

    @PutMapping("/project/{id}/team/{role}")
    public ResponseEntity updateProjectRoleForUsers (@PathVariable Long id, @PathVariable Role role, @Valid @RequestBody ProjectRoleDTO projectRoleDTO)
            throws AccessForbiddenException, ObjectNotFoundException {
        projectRolesService.updateProjectRoleSecured(id, role, projectRoleDTO);
        return ResponseEntity.ok().build();
    }
}
