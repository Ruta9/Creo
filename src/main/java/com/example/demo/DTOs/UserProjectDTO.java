package com.example.demo.DTOs;

import java.util.UUID;

public interface UserProjectDTO {

    Long getId();
    String getName();
    int getTeamSize();
    int getUnreadNotifications();
    int getActiveStories();
    int getActiveTasks();
}
