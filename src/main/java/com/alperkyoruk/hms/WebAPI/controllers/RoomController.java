package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Room.CreateRoomDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.Room;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("/addRoom")
    public Result addRoom(@RequestBody CreateRoomDto createRoomDto){
        return roomService.addRoom(createRoomDto);
    }

    @PostMapping("/deleteRoom")
    public Result deleteRoom(@RequestBody int roomId){
        return roomService.deleteRoom(roomId);
    }

    @PostMapping("/updateRoom")
    public Result updateRoom(@RequestBody GetRoomDto getRoomDto){
        return roomService.updateRoom(getRoomDto);
    }

    @GetMapping("/getRoomById")
    public DataResult<Room> getRoomById(@RequestParam int roomId){
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/getRooms")
    public DataResult<List<GetRoomDto>> getRooms(){
        return roomService.getAll();
    }

    @GetMapping("/getRoomsByType")
    public DataResult<List<GetRoomDto>> getRoomsByType(@RequestParam String type){
        return roomService.getAllByRoomType(type);
    }

    @GetMapping("/getRoomsByStatus")
    public DataResult<List<GetRoomDto>> getRoomsByStatus(@RequestParam String status){
        return roomService.getAllByStatus(status);
    }

    @GetMapping("/getRoomByRoomNumber")
    public DataResult<GetRoomDto> getRoomByRoomNumber(@RequestParam int roomNumber){
        return roomService.getByRoomNumber(roomNumber);
    }

    @GetMapping("/getRoomsByRoomCapacity")
    public DataResult<List<GetRoomDto>> getRoomsByRoomCapacity(@RequestParam int roomCapacity){
        return roomService.getAllByRoomCapacity(roomCapacity);
    }

    @GetMapping("/getRoomsByBedType")
    public DataResult<List<GetRoomDto>> getRoomsByBedType(@RequestParam String bedType){
        return roomService.getAllByBedType(bedType);
    }

    @GetMapping("/getById")
    public DataResult<GetRoomDto> getById(@RequestParam int id){
        return roomService.getById(id);
    }



}
