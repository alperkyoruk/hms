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
@Builder
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "position")
    private String position;

    @Column(name = "department")
    private String department;

    @Column(name = "status")
    private String status;

    @Column(name = "badge_number")
    private String badgeNumber;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Ticket> tickets;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "performance_rating")
    private double performanceRating;

}
