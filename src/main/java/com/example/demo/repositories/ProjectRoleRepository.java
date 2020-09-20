package com.example.demo.repositories;

import com.example.demo.DTOs.roles.UserRole;
import com.example.demo.data.Project;
import com.example.demo.data.ProjectRole;
import com.example.demo.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRoleRepository extends CrudRepository<ProjectRole, Long> {

    @Query(value = "SELECT r.role, \n" +
            "            CASE WHEN r.is_global THEN TRUE\n" +
            "            WHEN :user in\n" +
            "(SELECT user_id from PROJECT_PARTICIPANT_ROLE ppr where ppr.PROJECT_ROLE_ID = r.id\n" +
            ") THEN TRUE\n" +
            "            ELSE FALSE END isGranted\n" +
            "            FROM PROJECT_ROLE r\n" +
            "           WHERE r.project_id = :project", nativeQuery = true)
    List<UserRole> getUserRolesForProject(@Param("project") Long projectId, @Param("user") Long userId);

    ProjectRole findByProjectAndRole(Project project, Role role);



}
