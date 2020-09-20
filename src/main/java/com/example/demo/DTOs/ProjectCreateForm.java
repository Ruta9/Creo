package com.example.demo.DTOs;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ProjectCreateForm {

    @NotBlank
    String name;
    String description;
}
