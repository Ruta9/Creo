package com.example.demo.DTOs;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ProjectGeneralInformation {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private TeamMember owner;

    private String imageName;
}
