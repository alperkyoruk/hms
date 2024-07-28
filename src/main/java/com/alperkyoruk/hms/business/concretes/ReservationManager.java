package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.business.abstracts.ReservationService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.constants.ReservationMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.ReservationDao;
import com.alperkyoruk.hms.entities.DTOs.Reservation.CreateReservationDto;
import com.alperkyoruk.hms.entities.DTOs.Reservation.GetReservationDto;
import com.alperkyoruk.hms.entities.Guest;
import com.alperkyoruk.hms.entities.Reservation;
import com.alperkyoruk.hms.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationManager implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private RoomService roomService;

    @Autowired
    private GuestService guestService;

    public ReservationManager(ReservationDao reservationDao, RoomService roomService, GuestService guestService) {
        this.reservationDao = reservationDao;
        this.roomService = roomService;
        this.guestService = guestService;
    }
    @Override
    public Result addReservation(CreateReservationDto createReservationDto) {
        var reservationNumber = createReservationNumber();
        if(reservationNumber == null){
            return new ErrorResult(ReservationMessages.reservationNumberCouldNotBeCreated);
        }

        List<Room> rooms = createReservationDto.getRoomIds().stream()
                .map(roomId -> roomService.getRoomById(roomId).getData())
                .filter(room -> room != null)
                .collect(Collectors.toList());


        List<Guest> guests = createReservationDto.getGuestIds().stream()
                .map(guestId -> guestService.getGuestById(guestId).getData())
                .filter(guest -> guest != null)
                .collect(Collectors.toList());

        double totalAmount = rooms.stream()
                .mapToDouble(Room::getRoomPrice)
                .sum();

        Reservation reservation = Reservation.builder()
                .startDate(createReservationDto.getStartDate())
                .endDate(createReservationDto.getEndDate())
                .createdAt(new Date())
                .reservationNumber(reservationNumber)
                .status(createReservationDto.getStatus())
                .paymentStatus(createReservationDto.getPaymentStatus())
                .rooms(rooms)
                .guests(guests)
                .totalAmount(totalAmount)
                .reservationNotes(createReservationDto.getReservationNotes())
                .build();


        reservationDao.save(reservation);
        return new SuccessResult(ReservationMessages.reservationAddedSuccessfully);
    }

    @Override
    public Result deleteReservation(int id) {
        var reservation = reservationDao.findById(id);
        if(reservation == null){
            return new ErrorResult(ReservationMessages.reservationCannotBeFound);
        }

        reservationDao.delete(reservation);
        return new SuccessResult(ReservationMessages.reservationDeleted);
    }

    @Override
    public Result updateReservation(GetReservationDto getReservationDto) {
        var reservation = reservationDao.findById(getReservationDto.getId());
        if(reservation == null){
            return new ErrorResult(ReservationMessages.reservationCannotBeFound);
        }

        reservation.setReservationNotes(getReservationDto.getReservationNotes() == null ? reservation.getReservationNotes() : getReservationDto.getReservationNotes());
        reservation.setPaymentStatus(getReservationDto.getPaymentStatus() == null ? reservation.getPaymentStatus() : getReservationDto.getPaymentStatus());
        reservation.setStatus(getReservationDto.getStatus() == null ? reservation.getStatus() : getReservationDto.getStatus());
        reservation.setTotalAmount(getReservationDto.getTotalAmount() == 0 ? reservation.getTotalAmount() : getReservationDto.getTotalAmount());
        reservation.setEndDate(getReservationDto.getEndDate() == null ? reservation.getEndDate() : getReservationDto.getEndDate());
        reservation.setStartDate(getReservationDto.getStartDate() == null ? reservation.getStartDate() : getReservationDto.getStartDate());

        reservationDao.save(reservation);
        return new SuccessResult(ReservationMessages.reservationUpdated);
    }

    @Override
    public DataResult<GetReservationDto> getById(int id) {
        var reservation = reservationDao.findById(id);
        if(reservation == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }

        var returnReservation = new GetReservationDto(reservation);
        return new SuccessDataResult<>(returnReservation, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<GetReservationDto> getByGuestId(int guestId) {
        var reservation = reservationDao.findByGuestsId(guestId);
        if(reservation == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }

        var returnReservation = new GetReservationDto(reservation);
        return new SuccessDataResult<>(returnReservation, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetReservationDto>> getAllByRoomId(int roomId) {
        var reservations = reservationDao.findByRoomsId(roomId);
        if(reservations == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }
        var returnList = GetReservationDto.buildListGetReservationDto(reservations);
        return new SuccessDataResult<>(returnList, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetReservationDto>> getAllByCheckInDate(Date checkInDate) {
        var reservations = reservationDao.findAllByStartDate(checkInDate);
        if(reservations == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }
        var returnList = GetReservationDto.buildListGetReservationDto(reservations);
        return new SuccessDataResult<>(returnList, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<GetReservationDto> getByReservationNumber(String reservationNumber) {
        var reservation = reservationDao.findByReservationNumber(reservationNumber);
        if(reservation == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }

        var returnReservation = new GetReservationDto(reservation);
        return new SuccessDataResult<>(returnReservation, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetReservationDto>> getAllByStatus(String status) {
        var reservations = reservationDao.findAllByStatus(status);
        if(reservations == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }
        var returnList = GetReservationDto.buildListGetReservationDto(reservations);
        return new SuccessDataResult<>(returnList, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetReservationDto>> getAllByPaymentStatus(String paymentStatus) {
        var reservations = reservationDao.findAllByPaymentStatus(paymentStatus);
        if(reservations == null){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }
        var returnList = GetReservationDto.buildListGetReservationDto(reservations);
        return new SuccessDataResult<>(returnList, ReservationMessages.reservationSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetReservationDto>> getAll() {
        var reservations = reservationDao.findAll();
        if(reservations.isEmpty()){
            return new ErrorDataResult<>(ReservationMessages.reservationCannotBeFound);
        }
        var returnList = GetReservationDto.buildListGetReservationDto(reservations);
        return new SuccessDataResult<>(returnList, ReservationMessages.reservationSuccessfullyBrought);
    }

    public String createReservationNumber(){
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
}
