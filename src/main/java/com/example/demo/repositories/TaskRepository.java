package com.example.demo.repositories;

import com.example.demo.DTOs.Ticket;
import com.example.demo.data.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Query(value = "SELECT s.id, s.name, (SUBSTRING(s.description, 0, 80) || '...') as shortenedDescription, " +
            "CONCAT(user_assignee.firstname, ' ', user_assignee.lastname) as assignee, " +
            "CONCAT(user_creator.firstname, ' ', user_creator.lastname) as creator, " +
            "st.name as status, " +
            "TIMESTAMP_TO_STRING(s.created_date) as createdDate, " +
            "TIMESTAMP_TO_STRING(s.updated_date) as lastUpdatedDate, " +
            "s.created_date as createdDate_date, " +
            "s.updated_date as lastUpdatedDate_date, " +
            "FROM TASK s " +
            "JOIN USER user_assignee on user_assignee.id = s.assignee_id " +
            "JOIN USER user_creator on user_creator.id = s.creator_id " +
            "JOIN STATUS st on st.id = s.status_id " +
            "WHERE s.story_id = :story_id",
            nativeQuery = true)
    List<Ticket> getStoryTasks(Long story_id);
}
