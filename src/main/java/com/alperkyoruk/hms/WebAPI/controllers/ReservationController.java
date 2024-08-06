package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.ReservationService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Reservation.CreateReservationDto;
import com.alperkyoruk.hms.entities.DTOs.Reservation.GetReservationDto;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/addReservation")
    public Result addReservation(@RequestBody CreateReservationDto createReservationDto){
        return reservationService.addReservation(createReservationDto);
    }

    @PostMapping("/deleteReservation")
    public Result deleteReservation(@RequestBody int reservationId){
        return reservationService.deleteReservation(reservationId);
    }

    @PostMapping("/updateReservation")
    public Result updateReservation(@RequestBody GetReservationDto getReservationDto){
        return reservationService.updateReservation(getReservationDto);
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam int reservationId) {
        return reservationService.getById(reservationId);
    }

    @GetMapping("/getByGuestId")
    public DataResult<GetReservationDto> getByGuestId(@RequestParam int guestId) {
        return reservationService.getByGuestId(guestId);
    }

    @GetMapping("/getAllByRoomId")
    public DataResult<List<GetReservationDto>> getAllByRoomId(@RequestParam int roomId) {
        return reservationService.getAllByRoomId(roomId);
    }

    @GetMapping("/getAllByCheckInDate")
    public DataResult<List<GetReservationDto>> getAllByCheckInDate(@RequestParam Date checkInDate) {
        return reservationService.getAllByCheckInDate(checkInDate);
    }

    @GetMapping("/getByReservationNumber")
    public DataResult<GetReservationDto> getByReservationNumber(@RequestParam String reservationNumber) {
        return reservationService.getByReservationNumber(reservationNumber);
    }

    @GetMapping("/getReservations")
    public DataResult<List<GetReservationDto>> getReservations() {
        return reservationService.getAll();
    }

    @GetMapping("/getReservationsByStatus")
    public DataResult<List<GetReservationDto>> getReservationsByStatus(@RequestParam String status) {
        return reservationService.getAllByStatus(status);
    }

    @GetMapping("/getAllByPaymentStatus")
    public DataResult<List<GetReservationDto>> getAllByPaymentStatus(@RequestParam String paymentStatus) {
        return reservationService.getAllByPaymentStatus(paymentStatus);
    }




}
