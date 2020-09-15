package com.example.demo.controllers;

import com.example.demo.DTOs.ProjectCreateDTO;
import com.example.demo.data.Status;
import com.example.demo.services.ProjectService;
import com.example.demo.services.ProjectStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    @Autowired
    ProjectStatusesService projectStatusesService;

    @GetMapping("/project/story/all/{id}")
    public ResponseEntity<List<Status>> getStoryStatuses (@PathVariable Long id) {
        List<Status> storyStatuses = projectStatusesService.getProjectStoriesStatusesSecured(id);
        if (storyStatuses != null) return ResponseEntity.ok(storyStatuses);
        else return new ResponseEntity(new AbstractMap.SimpleEntry<>("error","Project does not exist or you do not have access to see this information"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/project/task/all/{id}")
    public ResponseEntity<List<Status>> getTaskStatuses (@PathVariable Long id) {
        List<Status> taskStatuses = projectStatusesService.getProjectTasksStatusesSecured(id);
        if (taskStatuses != null) return ResponseEntity.ok(taskStatuses);
        else return new ResponseEntity(new AbstractMap.SimpleEntry<>("error","Project does not exist or you do not have access to see this information"), HttpStatus.NOT_FOUND);

    }

    @PutMapping("/project/{id}")
    public ResponseEntity updateProjectStatuses (@PathVariable Long id, @Valid @RequestBody List<Status> statuses) {
        String message = projectStatusesService.updateStatuses(id, statuses);
        if (message == null) return new ResponseEntity(HttpStatus.OK);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }



}
