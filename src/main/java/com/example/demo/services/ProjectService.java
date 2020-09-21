package com.example.demo.services;

import com.example.demo.DTOs.*;
import com.example.demo.data.*;
import com.example.demo.enums.DefaultTicketStatuses;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.FileStorageException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.exceptions.UnsupportedFileFormatException;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.security.UserService;
import com.example.demo.services.filestorage.FileStorageService;
import com.example.demo.services.filestorage.LocalFileStorageService;
import com.example.demo.utils.FileExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectRoleRepository projectRoleRepository;

    private final UserService userService;
    private final SecurityService securityService;
    private final FileStorageService localFileStorageService;

    @Autowired
    public ProjectService (ProjectRepository projectRepository,
                           UserService userService,
                           SecurityService securityService,
                           ProjectRoleRepository projectRoleRepository,
                           LocalFileStorageService localFileStorageService){
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;

        this.securityService = securityService;
        this.userService = userService;
        this.localFileStorageService = localFileStorageService;

    }

    public List<UserProject> getUserProjects () throws ObjectNotFoundException {
        return projectRepository.findProjectsByParticipation(userService.getUser().getEmail());
    }

    public void createProject (ProjectCreateForm pr) throws ObjectNotFoundException {

        User user = userService.getUser();
        List<Status> statuses = DefaultTicketStatuses.getAllDefaultStatuses();

        // Create project
        Project project = new Project();
        project.setName(pr.getName());
        project.setDescription(pr.getDescription());
        project.setOwner(user);
        project.setTeam(Arrays.asList(user));

        for (Status s: statuses){
            s.setProject(project);
        }
        project.setStatuses(statuses);

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
    }

    public void updateProject(ProjectGeneralInformationForm projectForm) throws ObjectNotFoundException, UnsupportedFileFormatException, FileStorageException {
        Project project = getProject(projectForm.getProjectInformation().getId());
        if (projectForm.getProjectImage() != null){
            MultipartFile file = projectForm.getProjectImage();
            if (file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
                String newFilename = "PROJECT" + project.getId();
                localFileStorageService.store(file, newFilename);
                project.setImageName(newFilename + FileExtension.getExtension(file.getOriginalFilename()));
            }
            else throw new UnsupportedFileFormatException("Project image should be either .png or .jpeg");
        }
        else {
            localFileStorageService.delete(project.getImageName());
            project.setImageName(null);
        }
        project.setName(projectForm.getProjectInformation().getName());
        project.setDescription(projectForm.getProjectInformation().getDescription());
        project.setOwner(userService.getUser(projectForm.getProjectInformation().getOwner().getId()));
        saveProject(project);
    }

    public String getProjectNameSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = getProject(id);

        if (securityService.userHasAccessToProject(project)) {
            return project.getName();
        }
        else throw new AccessForbiddenException();
    }

    public ProjectGeneralInformation getProjectGeneralInfoSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = getProject(id);

        if (securityService.userHasAccessToProject(project)) {
                TeamMember owner = new TeamMember(
                        project.getOwner().getId(),
                        project.getOwner().getFirstname(),
                        project.getOwner().getLastname(),
                        project.getOwner().getEmail()
                );
                return new ProjectGeneralInformation(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        owner,
                        project.getImageName()
                );
        }
        else throw new AccessForbiddenException();
    }

    public List<TeamMember> getProjectTeamSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {
        Project project = getProject(id);

        if (securityService.userHasAccessToProject(project)) {
            return project.getTeam().stream()
                    .map(user -> new TeamMember(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail()))
                    .collect(Collectors.toList());
        }
        else throw new AccessForbiddenException();
    }

    public Project getProject (Long id) throws ObjectNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) return project.get();
        else throw new ObjectNotFoundException("No project found with id: " + id);
    }

    public AbstractMap.Entry<String, byte[]> getProjectImageSecured(Long id)
            throws ObjectNotFoundException, FileStorageException, AccessForbiddenException {
        Project project = getProject(id);
        if (securityService.userHasAccessToProject(project)) {
            if (project.getImageName() == null) return new AbstractMap.SimpleEntry<>(null, null);
            else return new AbstractMap.SimpleEntry<>(project.getImageName(), localFileStorageService.loadAsBytes(project.getImageName()));
        }
        else throw new AccessForbiddenException();
    }

    public void saveProject(Project project){
        projectRepository.save(project);
    }

}
