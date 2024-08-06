package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.RoomServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface RoomServiceOrderDao extends JpaRepository<RoomServiceOrder, Integer> {

    RoomServiceOrder findById(int id);

    List<RoomServiceOrder> findAllByRoomRoomNumber(int room);

    List<RoomServiceOrder> findAllByStatus(String status);

    List<RoomServiceOrder> findAllByOrderTimeBefore(LocalTime orderTime);

    List<RoomServiceOrder> findAllByOrderTimeAfter(LocalTime orderTime);

    List<RoomServiceOrder> findAllByGuestId(int guestId);

    List<RoomServiceOrder> findAllByOrderDate(Date date);
}
