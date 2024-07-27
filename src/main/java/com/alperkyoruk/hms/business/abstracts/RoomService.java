package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Room.CreateRoomDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.Room;

import java.util.List;

public interface RoomService {

    Result addRoom(CreateRoomDto createRoomDto);
    Result deleteRoom(int id);
    Result updateRoom(GetRoomDto getRoomDto);
    DataResult<GetRoomDto> getById(int id);
    DataResult<GetRoomDto> getByRoomNumber(int roomNumber);
    DataResult<List<GetRoomDto>> getAll();
    DataResult<List<GetRoomDto>> getAllByRoomType(String roomType);
    DataResult<List<GetRoomDto>> getAllByStatus(String status);
    DataResult<List<GetRoomDto>> getAllByRoomCapacity(int roomCapacity);
    DataResult<List<GetRoomDto>> getAllByBedType(String bedType);

    DataResult<Room> getRoomById(int id);
    DataResult<Room> getRoomByRoomNumber(int roomNumber);

}
