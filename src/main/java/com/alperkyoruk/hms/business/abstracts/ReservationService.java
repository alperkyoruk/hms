package com.alperkyoruk.hms.business.abstracts;


import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Reservation.CreateReservationDto;
import com.alperkyoruk.hms.entities.DTOs.Reservation.GetReservationDto;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Result addReservation(CreateReservationDto createReservationDto);
    Result deleteReservation(int id);
    Result updateReservation(GetReservationDto getReservationDto);
    DataResult<GetReservationDto> getById(int id);
    DataResult<List<GetReservationDto>> getAllByGuestId(int guestId);
    DataResult<List<GetReservationDto>> getAllByRoomId(int roomId);
    DataResult<List<GetReservationDto>> getAllByCheckInDate(Date checkInDate);
    DataResult<GetReservationDto> getByReservationNumber(String reservationNumber);
    DataResult<List<GetReservationDto>> getAllByStatus(String status);
    DataResult<List<GetReservationDto>> getAllByPaymentStatus(String paymentStatus);
    DataResult<List<GetReservationDto>> getAll();
}
