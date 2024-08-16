package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GuestDao extends JpaRepository<Guest, Integer> {
    Guest findById(int id);

    List<Guest> findAllByFirstNameAndLastNameContainsIgnoreCase(String firstName, String lastName);

    Guest findAllByLastName(String lastName);

    Guest findByEmail(String email);

    Guest findByPhoneNumber(String phoneNumber);

    List<Guest> findAllByVipStatus(String vipStatus);

    Guest findByPassportNumber(String passportNumber);

    Guest findByVipStatus(String vipStatus);

    List<Guest> findAllByCheckInDate(Date checkInDate);

    List<Guest> findAllByRoomId(int roomId);

    List<Guest> findAllByReservationReservationNumber(String reservationNumber);

    List<Guest> findAllByCheckOutDate(Date checkOutDate);

    List<Guest> findAllByStatus(String status);



}
