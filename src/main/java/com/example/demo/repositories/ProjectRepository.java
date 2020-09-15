package com.example.demo.repositories;

import com.example.demo.DTOs.UserProjectDTO;
import com.example.demo.data.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query(value = "SELECT p.id, p.name AS name, 0 AS teamSize, 0 AS unreadNotifications, 0 AS activeStories, 0 AS activeTasks " +
            "FROM PROJECT p " +
            "JOIN TEAM t on t.project_id = p.id " +
            "JOIN USER u on u.id = t.user_id " +
            "WHERE u.email = :userEmail", nativeQuery = true)
    List<UserProjectDTO> findProjectsByParticipation(@Param("userEmail") String userEmail);


}
