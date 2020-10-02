package com.example.demo.DTOs;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class StoryCreateForm {

    @NotBlank
    String name;

    String description;

    @NotNull
    long assigneeId;

    @NotNull
    long statusId;

}
