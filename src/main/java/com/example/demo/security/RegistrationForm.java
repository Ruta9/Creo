package com.example.demo.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotBlank(message = "First name can not be empty")
    private final String firstname;

    @NotBlank(message = "First name can not be empty")
    private final String lastname;

    @Size(min=8, message="Password should be at least 8 characters long")
    private final String password;

    @Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$", message="Email is not valid")
    private final String email;
}
