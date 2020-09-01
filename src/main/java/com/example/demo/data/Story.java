package com.example.demo.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
@RequiredArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;

    @NotBlank
    private String name;

    private String description;

    private Date createdDate;

    @OneToOne(optional=false)
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    private User creator;

    @OneToOne(optional=false)
    @JoinColumn(name="ASSIGNEE_ID", referencedColumnName = "ID")
    private User assignee;

    @NotBlank
    private String status;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID", referencedColumnName = "ID")
    private Project project;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "story")
    private Collection<Task> tasks;

    @PrePersist
    void createdDate(){
        this.createdDate = new Date();
    }
}
