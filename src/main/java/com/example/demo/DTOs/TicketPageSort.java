package com.example.demo.DTOs;

import lombok.Value;
import org.springframework.data.domain.Sort;

import java.util.List;

@Value
public class TicketPageSort {

    Sort.Direction direction;
    String[] fields;
}
