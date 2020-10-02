package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor(force = true)
@Data
@RequiredArgsConstructor
public class Story {

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

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="PROJECT_ID", referencedColumnName = "ID")
    private Project project;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "story")
    private Collection<Task> tasks;

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
