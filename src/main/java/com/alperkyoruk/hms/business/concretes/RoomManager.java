package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.constants.RoomMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.RoomDao;
import com.alperkyoruk.hms.entities.DTOs.Room.CreateRoomDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomManager implements RoomService {

    @Autowired
    private RoomDao roomDao;

    public RoomManager(RoomDao roomDao) {
        this.roomDao = roomDao;
    }


    @Override
    public Result addRoom(CreateRoomDto createRoomDto) {
        Room room = Room.builder()
                .roomDescription(createRoomDto.getRoomDescription())
                .roomNumber(createRoomDto.getRoomNumber())
                .roomType(createRoomDto.getRoomType())
                .roomPrice(createRoomDto.getRoomPrice())
                .roomCapacity(createRoomDto.getRoomCapacity())
                .roomStatus(createRoomDto.getRoomStatus())
                .bedType(createRoomDto.getBedType())
                .build();

        roomDao.save(room);
        return new SuccessResult(RoomMessages.RoomAddedSuccessfully);
    }

    @Override
    public Result deleteRoom(int id) {
        var room = roomDao.findById(id);
        if (room == null) {
            return new ErrorResult(RoomMessages.RoomCouldNotBeFound);
        }
        roomDao.delete(room);
        return new SuccessResult(RoomMessages.RoomDeletedSuccessfully);
    }

    @Override
    public Result updateRoom(GetRoomDto getRoomDto) {
        var room = roomDao.findById(getRoomDto.getId());
        if (room == null) {
            return new ErrorResult(RoomMessages.RoomCouldNotBeFound);
        }
        room.setRoomDescription(getRoomDto.getRoomDescription() == null  ? room.getRoomDescription() : getRoomDto.getRoomDescription());
        room.setRoomNumber(getRoomDto.getRoomNumber() == 0 ? room.getRoomNumber() : getRoomDto.getRoomNumber());
        room.setRoomType(getRoomDto.getRoomType() == null ? room.getRoomType() : getRoomDto.getRoomType());
        room.setRoomPrice(getRoomDto.getRoomPrice() == 0 ? room.getRoomPrice() : getRoomDto.getRoomPrice());
        room.setRoomCapacity(getRoomDto.getRoomCapacity() == 0 ? room.getRoomCapacity() : getRoomDto.getRoomCapacity());
        room.setRoomStatus(getRoomDto.getRoomStatus() == null ? room.getRoomStatus() : getRoomDto.getRoomStatus());
        room.setBedType(getRoomDto.getBedType() == null ? room.getBedType() : getRoomDto.getBedType());

        roomDao.save(room);
        return new SuccessResult(RoomMessages.RoomUpdatedSuccessfully);
    }

    @Override
    public DataResult<GetRoomDto> getById(int id) {
        var room = roomDao.findById(id);
        if (room == null) {
            return new ErrorDataResult<>(RoomMessages.RoomCouldNotBeFound);
        }
        var returnRoom = new GetRoomDto(room);
        return new SuccessDataResult<>(returnRoom, RoomMessages.RoomSuccessfullyBrought);
    }

    @Override
    public DataResult<GetRoomDto> getByRoomNumber(int roomNumber) {
        var room = roomDao.findByRoomNumber(roomNumber);
        if (room == null) {
            return new ErrorDataResult<>(RoomMessages.RoomCouldNotBeFound);
        }
        var returnRoom = new GetRoomDto(room);
        return new SuccessDataResult<>(returnRoom, RoomMessages.RoomSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomDto>> getAll() {
        var result = roomDao.findAll();
        if (result.isEmpty()) {
            return new ErrorDataResult<>(RoomMessages.RoomsNotFound);
        }
        List<GetRoomDto> returnList = GetRoomDto.buildListGetRoomDto(result);
        return new SuccessDataResult<>(returnList, RoomMessages.RoomsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByRoomType(String roomType) {
        var result = roomDao.findAllByRoomType(roomType);
        if (result.isEmpty()) {
            return new ErrorDataResult<>(RoomMessages.RoomsNotFound);
        }
        List<GetRoomDto> returnList = GetRoomDto.buildListGetRoomDto(result);
        return new SuccessDataResult<>(returnList, RoomMessages.RoomsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByStatus(String status) {
        var result = roomDao.findAllByRoomStatus(status);
        if (result.isEmpty()) {
            return new ErrorDataResult<>(RoomMessages.RoomsNotFound);
        }
        List<GetRoomDto> returnList = GetRoomDto.buildListGetRoomDto(result);
        return new SuccessDataResult<>(returnList, RoomMessages.RoomsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByRoomCapacity(int roomCapacity) {
        var result = roomDao.findAllByRoomCapacityGreaterThanEqual(roomCapacity);
        if (result.isEmpty()) {
            return new ErrorDataResult<>(RoomMessages.RoomsNotFound);
        }
        List<GetRoomDto> returnList = GetRoomDto.buildListGetRoomDto(result);
        return new SuccessDataResult<>(returnList, RoomMessages.RoomsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomDto>> getAllByBedType(String bedType) {
        var result = roomDao.findAllByBedType(bedType);
        if (result.isEmpty()) {
            return new ErrorDataResult<>(RoomMessages.RoomsNotFound);
        }
        List<GetRoomDto> returnList = GetRoomDto.buildListGetRoomDto(result);
        return new SuccessDataResult<>(returnList, RoomMessages.RoomsSuccessfullyBrought);
    }

    @Override
    public DataResult<Room> getRoomById(int id) {
        var room = roomDao.findById(id);
        if (room == null) {
            return new ErrorDataResult<>(RoomMessages.RoomCouldNotBeFound);
        }
        return new SuccessDataResult<>(room, RoomMessages.RoomSuccessfullyBrought);
    }

    @Override
    public DataResult<Room> getRoomByRoomNumber(int roomNumber) {
        var room = roomDao.findByRoomNumber(roomNumber);
        if (room == null) {
            return new ErrorDataResult<>(RoomMessages.RoomCouldNotBeFound);
        }
        return new SuccessDataResult<>(room, RoomMessages.RoomSuccessfullyBrought);
    }

    @Override
    public Result updateRoomStatus(Room room, String status) {
        room.setRoomStatus(status);
        roomDao.save(room);
        return new SuccessResult(RoomMessages.RoomStatusUpdatedSuccessfully);
    }


}
