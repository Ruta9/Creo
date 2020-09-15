package com.example.demo.data;

import com.example.demo.enums.TicketType;

import java.util.ArrayList;
import java.util.List;

public class DefaultStatuses {

    public static List<Status> getDefaultStatuses(TicketType ticketType) {
        List<Status> statuses = new ArrayList();
        statuses.add(new Status("Created", 1, ticketType));
        statuses.add(new Status("In Progress", 2, ticketType));
        statuses.add(new Status("On Hold", 3, ticketType));
        statuses.add(new Status("Testing", 4, ticketType));
        statuses.add(new Status("Done", 5, ticketType));
        statuses.add(new Status("Archived", 6, ticketType));
        return statuses;
    }

    public static List<Status> getAllDefaultStatuses(){
        List<Status> statuses = getDefaultStatuses(TicketType.STORY);
        statuses.addAll(getDefaultStatuses(TicketType.TASK));
        return statuses;
    }
}
