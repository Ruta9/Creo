package com.example.demo.data;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;

    @NotBlank(message="Project must have a name")
    private String name;

    private String description;

    private Date createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name="OWNER_ID", referencedColumnName="ID")
    private User owner;

    @ManyToMany
    @JoinTable(
            name="TEAM",
            joinColumns = @JoinColumn(name="PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    )
    private Collection<User> team;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private Collection<Story> stories;


    @PrePersist
    void createdDate(){
        this.createdDate = new Date();
    }


}
