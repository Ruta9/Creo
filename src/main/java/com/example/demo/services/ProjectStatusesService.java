package com.example.demo.services;

import com.example.demo.data.Project;
import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectStatusesService {

    private final StatusRepository statusRepository;

    private final SecurityService securityService;
    private final ProjectService projectService;

    @Autowired
    public ProjectStatusesService (SecurityService securityService,
                           StatusRepository statusRepository,
                           ProjectService projectService){
        this.securityService = securityService;
        this.statusRepository = statusRepository;
        this.projectService = projectService;

    }

    public List<Status> getProjectStoriesStatusesSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {
        Project project = projectService.getProject(id);
        if (securityService.userHasAccessToProject(project)) {
            return getStatuses(id, TicketType.STORY);
        }
        else throw new AccessForbiddenException();
    }

    public List<Status> getProjectTasksStatusesSecured(Long id)
            throws ObjectNotFoundException, AccessForbiddenException {
        Project project = projectService.getProject(id);
        if (securityService.userHasAccessToProject(project)) {
            return getStatuses(id, TicketType.TASK);
        }
        else throw new AccessForbiddenException();
    }

    private List<Status> getStatuses(Long id, TicketType ticketType) throws ObjectNotFoundException {
        List<Status> statuses = statusRepository.findStatusesByTicketTypeAndProject(ticketType, id);
        if (statuses.isEmpty()) throw new ObjectNotFoundException("No statuses were found for ticket type " + ticketType + " in project with id " + id);
        else return statuses;
    }

    public void updateStatusesSecured(Long id, List<Status> statuses)
            throws ObjectNotFoundException, AccessForbiddenException {
        Project project = projectService.getProject(id);
        if (securityService.userIsAnAdmin(project)){
                project.addStatuses(statuses);
                projectService.saveProject(project);
        }
        else throw new AccessForbiddenException();
    }

    public Status getStatus(Long id) throws ObjectNotFoundException {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) return status.get();
        else throw new ObjectNotFoundException("Status with id " + id + " not found");
    }
}
