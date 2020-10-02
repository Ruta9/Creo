package com.example.demo.DTOs;

import lombok.Value;

import java.util.List;

@Value
public class TicketPage {

    List<Ticket> tickets;
    long totalNumberOfRecords;
    long totalNumberOfPages;
    long firstPageRecord;
    long lastPageRecord;
    long currentPageNumber;
}
