package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.RoomServiceOrderService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.CreateRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.GetRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.RoomServiceOrder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/roomServiceOrders")
public class RoomServiceOrderController {

    private RoomServiceOrderService roomServiceOrderService;

    public RoomServiceOrderController(RoomServiceOrderService roomServiceOrderService){
        this.roomServiceOrderService = roomServiceOrderService;
    }

    @PostMapping("/addRoomServiceOrder")
    public Result addRoomServiceOrder(@RequestBody CreateRoomServiceOrderDto createRoomServiceOrderDto){
        return roomServiceOrderService.addRoomServiceOrder(createRoomServiceOrderDto);
    }

    @PostMapping("/deleteRoomServiceOrder")
    public Result deleteRoomServiceOrder(@RequestBody int roomServiceOrderId){
        return roomServiceOrderService.deleteRoomServiceOrder(roomServiceOrderId);
    }

    @PostMapping("/updateRoomServiceOrder")
    public Result updateRoomServiceOrder(@RequestBody GetRoomServiceOrderDto getRoomServiceOrderDto){
        return roomServiceOrderService.updateRoomServiceOrder(getRoomServiceOrderDto);
    }

    @GetMapping("/getRoomServiceOrderById")
    public DataResult<GetRoomServiceOrderDto> getRoomServiceOrderById(@RequestParam int roomServiceOrderId){
        return roomServiceOrderService.getByRoomServiceOrderId(roomServiceOrderId);
    }

    @GetMapping("/getRoomServiceOrders")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrders(){
        return roomServiceOrderService.getAll();
    }

    @GetMapping("/getRoomServiceOrdersByRoomNumber")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByRoomNumber(@RequestParam int roomNumber){
        return roomServiceOrderService.getAllByRoomServiceOrderRoomNumber(roomNumber);
    }

    @GetMapping("/getRoomServiceOrdersByGuestId")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByGuestId(@RequestParam int guestId){
        return roomServiceOrderService.getAllByGuestId(guestId);
    }

    @GetMapping("/getRoomServiceOrdersByStatus")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByStatus(@RequestParam String status){
        return roomServiceOrderService.getAllByRoomServiceOrderStatus(status);
    }

    @GetMapping("/getRoomServiceOrdersByDate")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByDate(@RequestParam Date date){
        return roomServiceOrderService.getAllByDate(date);
    }

    @GetMapping("/getRoomServiceOrdersByTimeBefore")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByTime(@RequestParam LocalTime time) {
        return roomServiceOrderService.getAllByOrderTimeBefore(time);
    }

    @GetMapping("/getRoomServiceOrdersByTimeAfter")
    public DataResult<List<GetRoomServiceOrderDto>> getRoomServiceOrdersByTimeAfter(@RequestParam LocalTime time) {
        return roomServiceOrderService.getAllByOrderTimeAfter(time);
    }


}
