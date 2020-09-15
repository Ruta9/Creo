package com.example.demo.services;

import com.example.demo.DTOs.UserProjectDTO;
import com.example.demo.data.Project;
import com.example.demo.data.ProjectRole;
import com.example.demo.enums.Role;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {

    private ProjectRepository projectRepository;
    private UserContext userContext;
    private ProjectRoleRepository projectRoleRepository;

    @Autowired
    public SecurityService (ProjectRepository projectRepository, UserContext userContext,
                            ProjectRoleRepository projectRoleRepository) {
        this.projectRepository = projectRepository;
        this.userContext = userContext;
        this.projectRoleRepository = projectRoleRepository;
    }

    public boolean userHasAccessToProject(Long id){
        List<UserProjectDTO> userProjectDTOList = projectRepository.findProjectsByParticipation(userContext.getEmail());
        Optional<UserProjectDTO> pr = userProjectDTOList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return pr.isPresent();
    }

    public boolean userIsAnAdmin(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) return false;

        ProjectRole projectRole = projectRoleRepository.findByProjectAndRole(project.get(), Role.PROJECTADMIN);
        if (projectRole == null) return false;

        return projectRole.getUsers().stream().anyMatch(u -> u.getEmail().equals(userContext.getEmail()));

    }
}
