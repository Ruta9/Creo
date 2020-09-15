package com.example.demo.DTOs.roles;

import com.example.demo.enums.Role;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class TeamRoleDTO {

    @NotNull
    Long id;

    @NotNull
    Role role;

    String roleDescription;

    @NotNull
    Boolean isGlobal;

    List<RoleGrantDTO> team;


}
