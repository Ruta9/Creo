package com.example.demo.services;

import com.example.demo.DTOs.StoryCreateForm;
import com.example.demo.DTOs.Ticket;
import com.example.demo.DTOs.TicketPage;
import com.example.demo.DTOs.TicketPageQuery;
import com.example.demo.data.Project;
import com.example.demo.data.Story;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.StoryRepository;
import com.example.demo.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    private final ProjectService projectService;
    private final SecurityService securityService;
    private final UserService userService;
    private final ProjectStatusesService projectStatusesService;
    private final Integer pageSize;

    public StoryService (StoryRepository storyRepository, SecurityService securityService,
                         ProjectService projectService,
                         @Value("${story-list.page-size:15}") Integer pageSize,
                         UserService userService,
                         ProjectStatusesService projectStatusesService){
        this.securityService = securityService;
        this.projectService = projectService;
        this.storyRepository = storyRepository;
        this.pageSize = pageSize;
        this.userService = userService;
        this.projectStatusesService = projectStatusesService;
    }

    private TicketPage getStoriesForProject(Long id, PageRequest pageRequest){
        Page<Ticket> tickets = storyRepository.getStoriesByPage(pageRequest, id);
        return convertPageToTicketPage(tickets);
    }

    public TicketPage getFilteredAndSortedStoriesForProjectSecured(Long id, int page, TicketPageQuery query)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = projectService.getProject(id);
        if (securityService.userHasAccessToProject(project)){
            //if no request body, then return the first page of all project's stories
            if (query == null) return getStoriesForProject(id, PageRequest.of(page, pageSize));

            // if sort object is not null, then prepare the sorting page request, else prepare a request without sort
            PageRequest pageRequest;

            if (query.getSort() != null){
                pageRequest = PageRequest.of(page, pageSize, query.getSort().getDirection(), query.getSort().getFields());
            }
            else pageRequest = PageRequest.of(page, pageSize);

            // if filter object is null, return all un-filtered stories of provided project
            if (query.getFilter() == null) return getStoriesForProject(id, pageRequest);
            else return convertPageToTicketPage(storyRepository.getFilteredAndSortedStoriesByPage(pageRequest, project, query.getFilter()));

        }
        else throw new AccessForbiddenException();
    }

    private long getFirstRecordNumber(long pageNumber, long totalNumberOfElements){
        if (totalNumberOfElements == 0 || (pageNumber * pageSize + 1) > totalNumberOfElements) return 0;
        else if (pageNumber == 0) return 1;
        else {
            return pageNumber * pageSize + 1;
        }
    }

    private TicketPage convertPageToTicketPage(Page<Ticket> page){
        long totalNumberOfPages = page.getTotalPages() == 0 ? 1 : page.getTotalPages();
        long firstRecordNumber = getFirstRecordNumber(page.getNumber(), page.getTotalElements());
        long lastRecordNumber = page.getTotalElements() == 0 || firstRecordNumber == 0 ? 0 : firstRecordNumber + page.getNumberOfElements() - 1;
        return new TicketPage(
                page.toList(),
                page.getTotalElements(),
                totalNumberOfPages,
                firstRecordNumber,
                lastRecordNumber,
                page.getNumber() + 1
        );
    }

    public void createStory(Long id, StoryCreateForm storyCreateForm)
            throws ObjectNotFoundException, AccessForbiddenException {

        Project project = projectService.getProject(id);

        if (securityService.userCanCreateStories(project)){
            Story story = new Story();
            story.setName(storyCreateForm.getName());
            story.setDescription(storyCreateForm.getDescription());
            story.setAssignee(userService.getUser(storyCreateForm.getAssigneeId()));
            story.setCreator(userService.getUser());
            story.setProject(project);
            story.setStatus(projectStatusesService.getStatus(storyCreateForm.getStatusId()));
            storyRepository.save(story);
        }
        else throw new AccessForbiddenException();


    }
}
