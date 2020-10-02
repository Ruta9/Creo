package com.example.demo.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Data
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

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
    @JoinColumn(name="STORY_ID", referencedColumnName = "ID")
    private Story story;

    @PrePersist
    void createdDate(){
        this.createdDate = new Date();
    }
}
