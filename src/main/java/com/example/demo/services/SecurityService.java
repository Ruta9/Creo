package com.example.demo.services;

import com.example.demo.DTOs.UserProject;
import com.example.demo.data.Project;
import com.example.demo.data.ProjectRole;
import com.example.demo.data.User;
import com.example.demo.enums.Role;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SecurityService {

    private final ProjectRoleRepository projectRoleRepository;
    private final UserRepository userRepository;

    private final UserContext userContext;

    @Autowired
    public SecurityService (UserContext userContext,
                            ProjectRoleRepository projectRoleRepository,
                            UserRepository userRepository) {
        this.userContext = userContext;
        this.projectRoleRepository = projectRoleRepository;
        this.userRepository = userRepository;
    }

    public boolean userHasAccessToProject(Project project){
        return project
                .getTeam()
                .stream()
                .anyMatch(teamMember -> teamMember.getId().equals(getUser().getId()));
    }

    public boolean userIsAnAdmin(Project project) {
        ProjectRole pr = getProjectRole(project, Role.PROJECTADMIN);
        if (pr == null) return false;
        return pr.getUsers().stream().anyMatch(u -> u.getId().equals(getUser().getId()));

    }

    public boolean userCanCreateStories(Project project){
        ProjectRole pr = getProjectRole(project, Role.STORYCREATOR);
        if (pr == null) return false;
        return pr.getUsers().stream().anyMatch(u -> u.getId().equals(getUser().getId()));
    }

    private User getUser(){
        return userRepository.findByEmail(userContext.getEmail());
    }

    private ProjectRole getProjectRole (Project project, Role role){
        return projectRoleRepository.findByProjectAndRole(project, role);
    }
}
