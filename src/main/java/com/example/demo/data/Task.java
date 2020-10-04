package com.example.demo.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(optional=false)
    @JoinColumn(name="CREATOR_ID", referencedColumnName = "ID")
    private User creator;

    @ManyToOne(optional=false)
    @JoinColumn(name="ASSIGNEE_ID", referencedColumnName = "ID")
    private User assignee;

    @ManyToOne(optional=false)
    @JoinColumn(name="STATUS_ID", referencedColumnName = "ID")
    private Status status;

    @ManyToOne(optional=false)
    @JoinColumn(name="STORY_ID", referencedColumnName = "ID")
    private Story story;

    @PrePersist
    void setDates(){
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    @PreUpdate
    void updateDates() {
        this.updatedDate = new Date();
    }
}
