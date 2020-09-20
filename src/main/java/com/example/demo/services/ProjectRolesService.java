package com.example.demo.services;

import com.example.demo.DTOs.roles.ProjectRoleGrant;
import com.example.demo.DTOs.TeamMember;
import com.example.demo.DTOs.roles.ProjectRoleDTO;
import com.example.demo.DTOs.roles.UserRole;
import com.example.demo.data.Project;
import com.example.demo.data.ProjectRole;
import com.example.demo.data.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.security.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectRolesService {

    private final ProjectRoleRepository projectRoleRepository;

    private final ProjectService projectService;
    private final UserService userService;

    private final SecurityService securityService;


    public ProjectRolesService (ProjectRoleRepository projectRoleRepository,
                                SecurityService securityService,
                                ProjectService projectService,
                                UserService userService){
        this.projectRoleRepository = projectRoleRepository;
        this.securityService = securityService;
        this.projectService = projectService;
        this.userService = userService;
    }

    public List<UserRole> getUserRolesForProjectSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = projectService.getProject(id);
        if (securityService.userHasAccessToProject(project)) {
            User user = userService.getUser();
            return projectRoleRepository.getUserRolesForProject(id, user.getId());
        }
        else throw new AccessForbiddenException();
    }

    public List<ProjectRoleDTO> getTeamRolesForProjectSecured(Long id, boolean includeOwner)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = projectService.getProject(id);
        if (securityService.userHasAccessToProject(project)) {

            List<ProjectRoleDTO> projectTeamRoles = new ArrayList<>();

            List<User> projectTeam = project.getTeam().stream()
                    .filter((t -> includeOwner || !t.getId().equals(project.getOwner().getId())) )
                    .collect(Collectors.toList());

            for (Role r : Role.values()) {

                ProjectRole projectRole = getProjectRole(project, r);
                if (projectRole == null) throw new ObjectNotFoundException("Project Role object was not found in the database");

                List<ProjectRoleGrant> projectRoleGrants = new ArrayList<>();

                for (User teamMember : projectTeam) {
                    Boolean hasRole = false;
                    if (projectRole.isGlobal()) hasRole = true;
                    else if (projectRole.getUsers().stream().anyMatch(u -> u.getId().equals(teamMember.getId())))
                        hasRole = true;
                    projectRoleGrants.add(new ProjectRoleGrant(new TeamMember(teamMember.getId(), teamMember.getFirstname(), teamMember.getLastname(), teamMember.getEmail()), hasRole));
                }

                projectTeamRoles.add(new ProjectRoleDTO(projectRole.getId(), r, r.getDescription(), projectRole.isGlobal(), projectRoleGrants));

            }
            return projectTeamRoles;
        }
        else throw new AccessForbiddenException();

    }

    public void updateProjectRoleSecured(Long project_id, Role role, ProjectRoleDTO teamRoleDTO)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = projectService.getProject(project_id);

        if (securityService.userIsAnAdmin(project)) {
            ProjectRole projectRole = getProjectRole(project, role);
            projectRole.setGlobal(teamRoleDTO.getIsGlobal());
            projectRole.updateUsers(
                    teamRoleDTO.getTeam().stream().filter(ProjectRoleGrant::getHasRole).map(tm -> {
                        User user;
                        try {
                            user = userService.getUser(tm.getTeamMemberDTO().getId());
                        } catch (ObjectNotFoundException e) {
                            user = null;
                        }
                        return user;
                    }).collect(Collectors.toCollection(ArrayList::new))
            );

            projectRoleRepository.save(projectRole);
        }
        else throw new AccessForbiddenException();
    };

    public ProjectRole getProjectRole(Project project, Role role) throws ObjectNotFoundException {
        ProjectRole projectRole = projectRoleRepository.findByProjectAndRole(project, role);
        if (projectRole == null) throw new ObjectNotFoundException("Project Role object was not found in the database");
        else return projectRole;
    }



}
