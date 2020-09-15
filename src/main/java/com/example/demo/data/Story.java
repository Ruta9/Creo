package com.example.demo.data;

import lombok.*;

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

    @OneToOne(optional=false)
    @JoinColumn(name="STATUS_ID", referencedColumnName = "ID")
    private Status status;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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
