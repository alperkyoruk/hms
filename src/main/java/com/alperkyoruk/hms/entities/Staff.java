package com.alperkyoruk.hms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "position")
    private String position;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Ticket> tickets;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "performance_rating")
    private double performanceRating;

}
