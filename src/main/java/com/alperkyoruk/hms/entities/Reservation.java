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
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "reservation_notes")
    private String reservationNotes;

    @Column(name = "discount_code")
    private String discountCode;

    @OneToMany(mappedBy = "rerservation")
    @JsonIgnore
    private List<Room> rooms;

    @OneToMany (mappedBy = "reservation")
    private List<Guest> guests;




}
