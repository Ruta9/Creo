package com.example.demo.DTOs;

import com.example.demo.enums.Role;

public interface UserRoleDTO {

        Role getRole();
        Boolean getIsGranted();
}
