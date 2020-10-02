package com.example.demo.DTOs;

import lombok.Value;

import java.util.Date;

@Value
public class TicketPageFilter {

    String name;
    String description;
    long assigneeId;
    long creatorId;
    Date lastUpdatedDate;
    Date createdDate;
    long[] statuses;

}
