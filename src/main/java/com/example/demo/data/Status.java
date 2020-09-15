package com.example.demo.data;

import com.example.demo.enums.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Status {

    public Status (String name, int statusOrder, TicketType ticketType){
        this.name = name;
        this.statusOrder = statusOrder;
        this.ticketType = ticketType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Status name can not be empty")
    private String name;

    @NotNull(message="Status must have a number indicating its order")
    private int statusOrder;

    @Enumerated(EnumType.STRING)
    @NotNull(message="A ticket type must be provided")
    private TicketType ticketType;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional=false)
    @JoinColumn(name="PROJECT_ID", referencedColumnName = "ID")
    private Project project;

}
