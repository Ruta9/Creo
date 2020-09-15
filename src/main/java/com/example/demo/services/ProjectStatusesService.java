package com.example.demo.services;

import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusesService {

    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;

    private final SecurityService securityService;

    @Autowired
    public ProjectStatusesService (ProjectRepository projectRepository,
                           SecurityService securityService,
                           StatusRepository statusRepository){
        this.projectRepository = projectRepository;
        this.securityService = securityService;
        this.statusRepository = statusRepository;

    }

    public List<Status> getProjectStoriesStatusesSecured(Long id){
        if (securityService.userHasAccessToProject(id)) {
            return getStatuses(id, TicketType.STORY);
        }
        else return null;
    }

    public List<Status> getProjectTasksStatusesSecured(Long id){
        if (securityService.userHasAccessToProject(id)) {
            return getStatuses(id, TicketType.TASK);
        }
        else return null;
    }

    private List<Status> getStatuses(Long id, TicketType ticketType){
        return statusRepository.findStatusesByTicketTypeAndProject(ticketType, id);
    }

    public String updateStatuses(Long id, List<Status> statuses){
        if (!securityService.userIsAnAdmin(id)) return "You do not have the rights to update this object";
        projectRepository.findById(id).ifPresent(p -> {
            p.addStatuses(statuses);
            projectRepository.save(p);
        });
        return null;
    }
}
