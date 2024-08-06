package com.alperkyoruk.hms.entities.DTOs.Reservation;

import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.Guest;
import com.alperkyoruk.hms.entities.Reservation;
import com.alperkyoruk.hms.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetReservationDto {
    private int id;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private String status;
    private double totalAmount;
    private String paymentStatus;
    private String reservationNotes;
    private String reservationNumber;
    private List<GetGuestDto> guests;
    private List<GetRoomDto> rooms;

    public GetReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.createdAt = reservation.getCreatedAt();
        this.status = reservation.getStatus();
        this.totalAmount = reservation.getTotalAmount();
        this.paymentStatus = reservation.getPaymentStatus();
        this.reservationNotes = reservation.getReservationNotes();
        this.reservationNumber = reservation.getReservationNumber();
        this.guests = GetGuestDto.buildListGetGuestDto(reservation.getGuests());
        this.rooms = GetRoomDto.buildListGetRoomDto(reservation.getRooms());

    }
    

    public static List<GetReservationDto> buildListGetReservationDto(List<Reservation> reservations) {
        return reservations.stream()
                .map(GetReservationDto::new)
                .collect(Collectors.toList());

    }



}


