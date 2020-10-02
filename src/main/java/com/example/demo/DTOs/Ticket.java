package com.example.demo.DTOs;

import java.util.Date;

public interface Ticket {

    Long getId();
    String getName();
    String getShortenedDescription();
    String getAssignee();
    String getCreator();
    String getStatus();
    String getCreatedDate();
    String getLastUpdatedDate();
    Date getLastUpdatedDate_date();
    Date getCreatedDate_date();


}
