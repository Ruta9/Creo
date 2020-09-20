package com.example.demo.controllers;

import com.example.demo.data.Status;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.ProjectStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    @Autowired
    ProjectStatusesService projectStatusesService;

    @GetMapping("/project/story/all/{id}")
    public ResponseEntity<List<Status>> getStoryStatuses (@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
        List<Status> storyStatuses = projectStatusesService.getProjectStoriesStatusesSecured(id);
        return ResponseEntity.ok(storyStatuses);
    }

    @GetMapping("/project/task/all/{id}")
    public ResponseEntity<List<Status>> getTaskStatuses (@PathVariable Long id)
            throws AccessForbiddenException, ObjectNotFoundException {
        List<Status> taskStatuses = projectStatusesService.getProjectTasksStatusesSecured(id);
        return ResponseEntity.ok(taskStatuses);

    }

    @PutMapping("/project/{id}")
    public ResponseEntity updateProjectStatuses (@PathVariable Long id, @Valid @RequestBody List<Status> statuses)
            throws AccessForbiddenException, ObjectNotFoundException {
            projectStatusesService.updateStatusesSecured(id, statuses);
            return ResponseEntity.ok().build();
    }

}
