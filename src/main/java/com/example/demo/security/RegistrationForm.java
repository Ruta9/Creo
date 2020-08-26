package com.example.demo.security;

import lombok.Data;

@Data
public class RegistrationForm {

    private final String email;
    private final String password;
    private final String firstname;
    private final String lastname;
}
