package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Integer> {

    Room findById(int id);

    Room findByRoomNumber(int roomNumber);

    List<Room> findAllByRoomType(String roomType);

    List<Room> findAllByRoomStatus(String roomStatus);

    List<Room> findAllByRoomPriceBefore(double roomPrice);

    List<Room> findAllByRoomCapacityGreaterThanEqual(int roomCapacity);

    List<Room> findAllByBedType(String bedType);

    List<Room> findAllByReservationReservationNumber(String reservationNumber);


}
