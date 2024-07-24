package com.alperkyoruk.hms.entities.DTOs.Guest;

import com.alperkyoruk.hms.entities.Reservation;
import com.alperkyoruk.hms.entities.Room;
import com.alperkyoruk.hms.entities.RoomServiceOrder;
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
public class CreateGuestDto {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String sex;

    private String phoneNumber;

    private String email;

    private String passportNumber;

    private Date checkInDate;

    private Date checkOutDate;

    private String vipStatus;

    private String loyaltyCardNumber;

    private int roomNumber;

    private String reservationNumber;





}