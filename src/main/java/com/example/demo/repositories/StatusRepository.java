package com.example.demo.repositories;

import com.example.demo.DTOs.UserProjectDTO;
import com.example.demo.data.Status;
import com.example.demo.enums.TicketType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {

    @Query(value = "SELECT s.id, s.name, s.status_order, s.ticket_type, s.project_id " +
            "FROM STATUS s " +
            "WHERE s.project_id = :project " +
            "AND s.ticket_type = :#{#type.name()} " +
            "order by s.status_order", nativeQuery = true)
    List<Status> findStatusesByTicketTypeAndProject(@Param("type") TicketType ticketType, @Param("project") Long id);

}
