package com.alperkyoruk.hms.entities.DTOs.Guest;

import com.alperkyoruk.hms.entities.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetGuestDto {

        private int id;

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


        public GetGuestDto(Guest guest) {
                this.id = guest.getId();
                this.firstName = guest.getFirstName();
                this.lastName = guest.getLastName();
                this.dateOfBirth = guest.getDateOfBirth();
                this.sex = guest.getSex();
                this.phoneNumber = guest.getPhoneNumber();
                this.email = guest.getEmail();
                this.passportNumber = guest.getPassportNumber();
                this.checkInDate = guest.getCheckInDate();
                this.checkOutDate = guest.getCheckOutDate();
                this.vipStatus = guest.getVipStatus();
                this.loyaltyCardNumber = guest.getLoyaltyCardNumber();
                this.roomNumber = guest.getRoom().getRoomNumber();
                this.reservationNumber = guest.getReservation().getReservationNumber();

        }

        public static List<GetGuestDto> buildListGetGuestDto(List<Guest> guests) {
                return guests.stream()
                        .map(GetGuestDto::new)
                        .collect(Collectors.toList());

        }



}


