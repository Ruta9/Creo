package com.example.demo.controllers;

import com.example.demo.DTOs.StoryCreateForm;
import com.example.demo.DTOs.TicketPage;
import com.example.demo.DTOs.TicketPageQuery;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;

    @PostMapping("/project/{id}/{page}")
    public ResponseEntity<TicketPage> getStoriesForProject(@PathVariable Long id, @PathVariable int page, @RequestBody(required=false) TicketPageQuery query)
            throws AccessForbiddenException, ObjectNotFoundException {
        return ResponseEntity.ok(storyService.getFilteredAndSortedStoriesForProjectSecured(id, page, query));
    }

    @PostMapping("/project/{id}")
    public ResponseEntity createStory(@PathVariable Long id, @Valid @RequestBody StoryCreateForm storyCreateForm) throws AccessForbiddenException, ObjectNotFoundException {
        storyService.createStory(id, storyCreateForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
