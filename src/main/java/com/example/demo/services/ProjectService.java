package com.example.demo.services;

import com.example.demo.DTOs.ProjectCreateDTO;
import com.example.demo.DTOs.UserProjectDTO;
import com.example.demo.data.*;
import com.example.demo.enums.Role;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserContext userContext;

    private final SecurityService securityService;

    private final ProjectRoleRepository projectRoleRepository;

    @Autowired
    public ProjectService (ProjectRepository projectRepository,
                           UserContext userContext, UserRepository userRepository,
                           SecurityService securityService,
                           ProjectRoleRepository projectRoleRepository){
        this.projectRepository = projectRepository;
        this.userContext = userContext;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.projectRoleRepository = projectRoleRepository;

    }

    public List<UserProjectDTO> getUserProjects () {
        return projectRepository.findProjectsByParticipation(userContext.getEmail());
    }

    public Project createProject (ProjectCreateDTO pr) {

        User user = userRepository.findByEmail(userContext.getEmail());
        List<Status> statuses = DefaultStatuses.getAllDefaultStatuses();

        // Create project
        Project project = new Project();
        project.setName(pr.getName());
        project.setDescription(pr.getDescription());
        project.setOwner(user);
        for (Status s: statuses){
            s.setProject(project);
        }
        project.setStatuses(statuses);
        project.setTeam(Arrays.asList(user));
        project = projectRepository.save(project);

        //Assign roles to the owner
        for(Role r: Role.values()){
            ProjectRole projectRole = new ProjectRole();
            projectRole.setGlobal(false);
            projectRole.setProject(project);
            projectRole.setUsers(Arrays.asList(user));
            projectRole.setRole(r);
            projectRoleRepository.save(projectRole);
        }
        return project;
    }

    public String getProjectNameSecured(Long id){
        if (securityService.userHasAccessToProject(id)) {
            Optional<Project> project = projectRepository.findById(id);
            if (project.isPresent()) return project.get().getName();
        }
        return null;
    }

}
