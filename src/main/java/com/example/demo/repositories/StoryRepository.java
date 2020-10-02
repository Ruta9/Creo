package com.example.demo.repositories;

import com.example.demo.DTOs.Ticket;
import com.example.demo.data.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface StoryRepository extends PagingAndSortingRepository<Story, Long>, CustomizedStoryRepository{

    @Query(value = "SELECT s.id, s.name, (SUBSTRING(s.description, 0, 80) || '...') as shortenedDescription, " +
            "CONCAT(user_assignee.firstname, ' ', user_assignee.lastname) as assignee, " +
            "CONCAT(user_creator.firstname, ' ', user_creator.lastname) as creator, " +
            "st.name as status, " +
            "TIMESTAMP_TO_STRING(s.created_date) as createdDate, " +
            "TIMESTAMP_TO_STRING(s.updated_date) as lastUpdatedDate, " +
            "s.created_date as createdDate_date, " +
            "s.updated_date as lastUpdatedDate_date, " +
            "FROM STORY s " +
            "JOIN USER user_assignee on user_assignee.id = s.assignee_id " +
            "JOIN USER user_creator on user_creator.id = s.creator_id " +
            "JOIN STATUS st on st.id = s.status_id " +
            "WHERE s.project_id = :project_id",
            countQuery = "SELECT count(*) FROM STORY s WHERE s.project_id = :project_id",
            nativeQuery = true)
    Page<Ticket> getStoriesByPage(Pageable pageable, Long project_id);

    @Query(value = "SELECT s.id, s.name, (SUBSTRING(s.description, 0, 80) || '...') as shortenedDescription, " +
            "CONCAT(user_assignee.firstname, ' ', user_assignee.lastname) as assignee, " +
            "CONCAT(user_creator.firstname, ' ', user_creator.lastname) as creator, " +
            "st.name as status, " +
            "TIMESTAMP_TO_STRING(s.created_date) as createdDate, " +
            "TIMESTAMP_TO_STRING(s.updated_date) as lastUpdatedDate, " +
            "s.created_date as createdDate_date, " +
            "s.updated_date as lastUpdatedDate_date, " +
            "FROM STORY s " +
            "JOIN USER user_assignee on user_assignee.id = s.assignee_id " +
            "JOIN USER user_creator on user_creator.id = s.creator_id " +
            "JOIN STATUS st on st.id = s.status_id " +
            "WHERE s.project_id = :project_id " +
            "AND (:name is NULL OR (TRIM(:name) like '*%' AND LOWER(s.name) like CONCAT('%', TRIM(LOWER(SUBSTRING(:name,2))), '%') )" +
            "OR LOWER(s.name) like CONCAT(TRIM(LOWER(:name)), '%') ) " +
            "AND (:description is NULL OR (TRIM(:description) like '*%' AND LOWER(s.description) like CONCAT('%', TRIM(LOWER(SUBSTRING(:description,2))), '%') )" +
            "OR LOWER(s.description) like CONCAT(TRIM(LOWER(:description)), '%') ) " +
            "AND (:assignee_id = 0 OR s.assignee_id = :assignee_id) " +
            "AND (:creator_id = 0 OR s.creator_id = :creator_id) " +
            "AND (:created_date is NULL OR (YEAR(s.created_date) = YEAR(:created_date) AND " +
            "MONTH(s.created_date) = MONTH(:created_date) AND DAY(s.created_date) = DAY(:created_date) )) " +
            "AND (:last_updated_date is NULL OR (YEAR(s.updated_date) = YEAR(:last_updated_date) AND " +
            "MONTH(s.updated_date) = MONTH(:last_updated_date) AND DAY(s.updated_date) = DAY(:last_updated_date) )) " +
            "AND st.id IN (:statuses) ",
            countQuery = "SELECT count(s.*) " +
                    "FROM STORY s " +
                    "JOIN USER user_assignee on user_assignee.id = s.assignee_id " +
                    "JOIN USER user_creator on user_creator.id = s.creator_id " +
                    "JOIN STATUS st on st.id = s.status_id " +
                    "WHERE s.project_id = :project_id " +
                    "AND (:name is NULL OR (TRIM(:name) like '*%' AND LOWER(s.name) like CONCAT('%', TRIM(LOWER(SUBSTRING(:name,2))), '%') )" +
                    "OR LOWER(s.name) like CONCAT(TRIM(LOWER(:name)), '%') ) " +
                    "AND (:description is NULL OR (TRIM(:description) like '*%' AND LOWER(s.description) like CONCAT('%', TRIM(LOWER(SUBSTRING(:description,2))), '%') )" +
                    "OR LOWER(s.description) like CONCAT(TRIM(LOWER(:description)), '%') ) " +
                    "AND (:assignee_id = 0 OR s.assignee_id = :assignee_id) " +
                    "AND (:creator_id = 0 OR s.creator_id = :creator_id) " +
                    "AND (:created_date is NULL OR (YEAR(s.created_date) = YEAR(:created_date) AND " +
                    "MONTH(s.created_date) = MONTH(:created_date) AND DAY(s.created_date) = DAY(:created_date) )) " +
                    "AND (:last_updated_date is NULL OR (YEAR(s.updated_date) = YEAR(:last_updated_date) AND " +
                    "MONTH(s.updated_date) = MONTH(:last_updated_date) AND DAY(s.updated_date) = DAY(:last_updated_date) )) " +
                    "AND st.id IN (:statuses) ",
            nativeQuery = true)
    Page<Ticket> getFilteredAndSortedStoriesByPage(Pageable pageable, long project_id,
                                                   String name, String description,
                                                   long assignee_id, long creator_id, Date created_date, Date last_updated_date,
                                                   long[] statuses);

}
