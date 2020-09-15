package com.example.demo.DTOs.roles;

import com.example.demo.DTOs.TeamMemberDTO;
import lombok.Value;

@Value
public class RoleGrantDTO {

    TeamMemberDTO teamMemberDTO;
    Boolean hasRole;
}
