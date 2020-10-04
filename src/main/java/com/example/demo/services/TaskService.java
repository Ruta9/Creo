package com.example.demo.services;

import com.example.demo.DTOs.Ticket;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final SecurityService securityService;
    private final ProjectService projectService;

    private final TaskRepository taskRepository;


    public List<Ticket> getStoryTasksSecured(Long projectId, Long storyId)
            throws AccessForbiddenException, ObjectNotFoundException {
        if (securityService.userHasAccessToProject(projectService.getProject(projectId))){
            return taskRepository.getStoryTasks(storyId);
        }
        else throw new AccessForbiddenException();

    }


}
