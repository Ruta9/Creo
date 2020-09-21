package com.example.demo.data;

import com.example.demo.enums.ProjectDefaultImage;
import com.example.demo.validation.StatusSizeConstraint;
import com.example.demo.validation.StatusUniquenessConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Project must have a name")
    private String name;

    private String description;

    private Date createdDate;

    private String imageName;

    @ManyToOne(optional = false)
    @JoinColumn(name="OWNER_ID", referencedColumnName="ID")
    private User owner;

    @ManyToMany
    @JoinTable(
            name="TEAM",
            joinColumns = @JoinColumn(name="PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    )
    private List<User> team;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private Collection<Story> stories;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @NotNull
    @StatusUniquenessConstraint
    @StatusSizeConstraint
    private Collection<Status> statuses;


    @PrePersist
    void createdDate(){
        this.createdDate = new Date();
        setImage();
    }

    @PreUpdate
    void setImage() {
        if (this.imageName == null) {
            this.imageName = ProjectDefaultImage.getRandomImage().getName();
        }
    }

    public void addStatuses(List<Status> statuses){
        if (this.statuses != null) {
            this.statuses.clear();
            this.statuses.addAll(statuses);
        }
        else this.statuses = statuses;
        statuses.forEach(s -> {
            s.setProject(this);
        });
    }


}
