package com.example.demo.DTOs;

import lombok.Value;

@Value
public class TicketPageQuery {

    TicketPageFilter filter;
    TicketPageSort sort;
}
