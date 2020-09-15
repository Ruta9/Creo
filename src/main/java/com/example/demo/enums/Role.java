package com.example.demo.enums;

public enum Role {

    TEAMADMIN("A person who can add new and remove old team members."),
    STORYCREATOR("A person who can create and delete stories."),
    TASKCREATOR("A person who can create and delete tasks."),
    PROJECTADMIN("The project owner.");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
