package com.example.demo.DTOs.roles;

import com.example.demo.enums.Role;

public interface UserRole {

        Role getRole();
        Boolean getIsGranted();
}
