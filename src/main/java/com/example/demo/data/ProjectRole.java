package com.example.demo.data;

import com.example.demo.DTOs.TeamMemberDTO;
import com.example.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name="PROJECT_ID", referencedColumnName="ID")
    private Project project;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @NotNull
    private boolean isGlobal;

    @ManyToMany
    @JoinTable(
            name="PROJECT_PARTICIPANT_ROLE",
            joinColumns = @JoinColumn(name="PROJECT_ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    )
    private List<User> users;

    public void updateUsers(List<User> userList){
        this.users.clear();
        this.users.addAll(userList);
    }




}
