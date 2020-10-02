package com.example.demo.repositories;

import com.example.demo.DTOs.Ticket;
import com.example.demo.DTOs.TicketPageFilter;
import com.example.demo.data.Project;
import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;
import com.example.demo.exceptions.AccessForbiddenException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.services.ProjectStatusesService;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CustomizedStoryRepositoryImpl implements CustomizedStoryRepository{

    private final StoryRepository storyRepository;
    private final StatusRepository statusRepository;

    @Override
    public Page<Ticket> getFilteredAndSortedStoriesByPage(Pageable pageable, Project project, TicketPageFilter filters) {
        long[] statuses;
        if (filters.getStatuses() == null || filters.getStatuses().length == 0 ){
            statuses = project.getStatuses().stream().mapToLong(Status::getId).toArray();
        }
        else statuses = filters.getStatuses();
        return storyRepository.getFilteredAndSortedStoriesByPage(pageable, project.getId(),
                StringUtils.isBlank(filters.getName()) ? null : filters.getName(),
                StringUtils.isBlank(filters.getDescription()) ? null : filters.getDescription(),
                filters.getAssigneeId(),
                filters.getCreatorId(),
                filters.getCreatedDate(),
                filters.getLastUpdatedDate(),
                statuses
        );
    }
}
