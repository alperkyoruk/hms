package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {
    Reservation findById(int id);

    Reservation findByReservationNumber(String reservationNumber);

    List<Reservation> findAllByStartDate(Date checkInDate);

    List<Reservation> findAllByStatus(String status);

    List<Reservation> findAllByPaymentStatus(String paymentStatus);




}
