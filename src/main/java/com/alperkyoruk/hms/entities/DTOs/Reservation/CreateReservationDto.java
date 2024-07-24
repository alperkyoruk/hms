package com.alperkyoruk.hms.entities.DTOs.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateReservationDto {

    private Date startDate;

    private Date endDate;

    private Date createdAt;

    private String status;

    private double totalAmount;

    private String paymentStatus;

    private String reservationNotes;




}
