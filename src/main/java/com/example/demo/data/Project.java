package com.example.demo.data;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
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

    private String name;
    private String description;
    private Date createdDate;

    @ManyToOne
    private User creator;

    @PrePersist
    void createdDate(){
        this.createdDate = new Date();
    }


}
