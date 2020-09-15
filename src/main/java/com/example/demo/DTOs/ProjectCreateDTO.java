package com.example.demo.DTOs;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ProjectCreateDTO {

    @NotBlank
    String name;
    String description;
}
