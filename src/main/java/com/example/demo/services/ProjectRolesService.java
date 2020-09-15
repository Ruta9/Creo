package com.example.demo.services;

import com.example.demo.DTOs.roles.RoleGrantDTO;
import com.example.demo.DTOs.TeamMemberDTO;
import com.example.demo.DTOs.roles.TeamRoleDTO;
import com.example.demo.DTOs.UserRoleDTO;
import com.example.demo.data.Project;
import com.example.demo.data.ProjectRole;
import com.example.demo.data.User;
import com.example.demo.enums.Role;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.UserContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectRolesService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectRoleRepository projectRoleRepository;

    private final SecurityService securityService;
    private final UserContext userContext;


    public ProjectRolesService (ProjectRepository projectRepository,
                                UserContext userContext,
                                UserRepository userRepository,
                                ProjectRoleRepository projectRoleRepository,
                                SecurityService securityService){
        this.projectRepository = projectRepository;
        this.userContext = userContext;
        this.userRepository = userRepository;
        this.projectRoleRepository = projectRoleRepository;
        this.securityService = securityService;

    }

    public List<UserRoleDTO> getUserRolesForProject(Long id){
        if (!securityService.userHasAccessToProject(id)) return null;
        User user = userRepository.findByEmail(userContext.getEmail());
        return projectRoleRepository.getUserRolesForProject(id, user.getId());
    }

    public List<TeamRoleDTO> getTeamRolesForProject(Long id){
        if (!securityService.userHasAccessToProject(id)) return null;
        List<TeamRoleDTO> projectTeamRoles = new ArrayList<>();
        Optional<Project> pr = projectRepository.findById(id);
        User user = userRepository.findByEmail(userContext.getEmail());

        if (pr.isPresent()){

            Project project = pr.get();

            //Team roles can only be seen by the admin. Admin should not be able to see himself

            List<User> projectTeam = project.getTeam().stream().filter(t -> t.getId() != user.getId()).collect(Collectors.toList());

            for (Role r : Role.values()){
                ProjectRole projectRole = projectRoleRepository.findByProjectAndRole(project, r);
                List<RoleGrantDTO> roleGrantDTOS = new ArrayList<>();

                for (User teamMember: projectTeam){
                    Boolean hasRole = false;
                    if (projectRole.isGlobal()) hasRole = true;
                    else if (projectRole.getUsers().stream().anyMatch(u -> u.getId() == teamMember.getId())) hasRole = true;
                    roleGrantDTOS.add(new RoleGrantDTO(new TeamMemberDTO(teamMember.getId(), teamMember.getFirstname(), teamMember.getLastname(), teamMember.getEmail()), hasRole));
                }

                projectTeamRoles.add(new TeamRoleDTO(projectRole.getId(), r, r.getDescription(), projectRole.isGlobal(), roleGrantDTOS));
            }
        }
        else {
            projectTeamRoles = null;
        }
        return projectTeamRoles;
    }


    public String updateProjectRole (Long project_id, Role role, TeamRoleDTO teamRoleDTO){

        if (!securityService.userIsAnAdmin(project_id)) return "You do not have the rights to update this object";

        Optional<Project> project = projectRepository.findById(project_id);
        if (project.isPresent()) {
            ProjectRole projectRole = projectRoleRepository.findByProjectAndRole(project.get(), role);
            projectRole.setGlobal(teamRoleDTO.getIsGlobal());
            projectRole.updateUsers(
                    teamRoleDTO.getTeam().stream().filter(RoleGrantDTO::getHasRole).map(tm -> {
                        Optional<User> user = userRepository.findById(tm.getTeamMemberDTO().getId());
                        return user.orElse(null);
                    }).collect(Collectors.toCollection(ArrayList::new))
            );

            projectRoleRepository.save(projectRole);
        }
        else return "Project not found";
        return null;
    };


}
