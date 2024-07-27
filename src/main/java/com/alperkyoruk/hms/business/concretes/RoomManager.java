package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Room.CreateRoomDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomManager implements RoomService {

    @Override
    public Result addRoom(CreateRoomDto createRoomDto) {
        return null;
    }

    @Override
    public Result deleteRoom(int id) {
        return null;
    }

    @Override
    public Result updateRoom(GetRoomDto getRoomDto) {
        return null;
    }

    @Override
    public DataResult<GetRoomDto> getById(int id) {
        return null;
    }

    @Override
    public DataResult<GetRoomDto> getByRoomNumber(int roomNumber) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByRoomType(String roomType) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByStatus(String status) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByRoomCapacity(int roomCapacity) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByBedType(String bedType) {
        return null;
    }

    @Override
    public DataResult<Room> getRoomById(int id) {
        return null;
    }

}
