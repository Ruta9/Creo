package com.example.demo.DTOs.roles;

import com.example.demo.DTOs.TeamMember;
import lombok.Value;

@Value
public class ProjectRoleGrant {

    TeamMember teamMemberDTO;
    Boolean hasRole;
}
