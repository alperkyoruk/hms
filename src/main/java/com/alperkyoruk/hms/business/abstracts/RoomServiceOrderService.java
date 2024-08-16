package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.CreateRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.GetRoomServiceOrderDto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface RoomServiceOrderService {
    Result addRoomServiceOrder(CreateRoomServiceOrderDto createRoomServiceOrderDto);

    Result deleteRoomServiceOrder(int id);

    Result updateRoomServiceOrder(GetRoomServiceOrderDto getRoomServiceOrderDto);

    DataResult<GetRoomServiceOrderDto> getById(int id);

    DataResult<GetRoomServiceOrderDto> getByRoomServiceOrderId(int roomServiceOrderId);

    DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderStatus(String roomServiceOrderStatus);

    DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderRoomNumber(int roomNumber);

    DataResult<List<GetRoomServiceOrderDto>> getAllByGuestId(int guestId);

    DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeBefore(LocalTime orderTime);

    DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeAfter(LocalTime orderTime);

    DataResult<GetRoomServiceOrderDto> CalculateEstimatedTime(int id);

    DataResult<List<GetRoomServiceOrderDto>> getAll();

    DataResult<List<GetRoomServiceOrderDto>> getAllByDate(Date date);

    DataResult<List<GetRoomServiceOrderDto>> getAllByGuest();

    void deleteAllByOrderDateBefore();
}
