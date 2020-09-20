package com.example.demo.DTOs;

import lombok.Value;

@Value
public class TeamMember {

    Long id;
    String firstname;
    String lastname;
    String email;

}
