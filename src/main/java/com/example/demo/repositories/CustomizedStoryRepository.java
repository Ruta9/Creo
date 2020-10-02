package com.example.demo.repositories;

import com.example.demo.DTOs.Ticket;
import com.example.demo.DTOs.TicketPageFilter;
import com.example.demo.data.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedStoryRepository {

    Page<Ticket> getFilteredAndSortedStoriesByPage(Pageable pageable, Project project, TicketPageFilter filters) ;

}
