package com.example.demo.controllers;

import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/project/{projectId}/story/{storyId}")
    public ResponseEntity createStory(@PathVariable Long projectId, @PathVariable Long storyId) throws AccessForbiddenException, ObjectNotFoundException {
        return ResponseEntity.ok(taskService.getStoryTasksSecured(projectId, storyId));
    }

}
