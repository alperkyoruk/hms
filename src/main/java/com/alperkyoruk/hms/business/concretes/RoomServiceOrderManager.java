package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.MenuItemService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.abstracts.RoomServiceOrderService;
import com.alperkyoruk.hms.business.constants.RoomServiceOrderMessages;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.core.result.SuccessResult;
import com.alperkyoruk.hms.dataAccess.RoomServiceOrderDao;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.CreateRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.GetRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.MenuItem;
import com.alperkyoruk.hms.entities.RoomServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceOrderManager implements RoomServiceOrderService {

    @Autowired
    private RoomServiceOrderDao roomServiceOrderDao;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private RoomService roomService;

    public RoomServiceOrderManager(RoomServiceOrderDao roomServiceOrderDao, MenuItemService menuItemService, RoomService roomService) {
        this.roomServiceOrderDao = roomServiceOrderDao;
        this.menuItemService = menuItemService;
        this.roomService = roomService;
    }
    @Override
    public Result addRoomServiceOrder(CreateRoomServiceOrderDto createRoomServiceOrderDto) {

        List<MenuItem> menuItems = createRoomServiceOrderDto.getMenuItemIds().stream()
                .map(menuItemId -> menuItemService.getMenuItemById(menuItemId).getData())
                .filter(menuItem -> menuItem != null)
                .collect(Collectors.toList());

        var room = roomService.getRoomById(createRoomServiceOrderDto.getRoomId()).getData();



        RoomServiceOrder roomServiceOrder = RoomServiceOrder.builder()
                .orderDate(createRoomServiceOrderDto.getOrderDate())
                .orderTime(createRoomServiceOrderDto.getOrderTime())
                .comment(createRoomServiceOrderDto.getComment())
                .estimatedTime(createRoomServiceOrderDto.getEstimatedTime())
                .menuItems(menuItems)
                .room(room)
                .status(createRoomServiceOrderDto.getStatus())
                .totalPrice(menuItems.stream().mapToDouble(MenuItem::getPrice).sum())
                .build();

        roomServiceOrderDao.save(roomServiceOrder);
        return new SuccessResult(RoomServiceOrderMessages.roomServiceOrderAddedSuccessfully);
    }

    @Override
    public Result deleteRoomServiceOrder(int id) {
        var roomServiceOrderResponse = roomServiceOrderDao.findById(id);
        if(roomServiceOrderResponse == null){
            return new SuccessResult(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        roomServiceOrderDao.delete(roomServiceOrderResponse);
        return new SuccessResult(RoomServiceOrderMessages.roomServiceOrderDeletedSuccessfully);
    }

    @Override
    public Result updateRoomServiceOrder(GetRoomServiceOrderDto getRoomServiceOrderDto) {
        return null;
    }

    @Override
    public DataResult<GetRoomServiceOrderDto> getById(int id) {
        return null;
    }

    @Override
    public DataResult<GetRoomServiceOrderDto> getByRoomServiceOrderId(int roomServiceOrderId) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderStatus(String roomServiceOrderStatus) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderRoomNumber(int roomNumber) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByGuestId(int guestId) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeBefore(LocalTime orderTime) {
        return null;
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeAfter(LocalTime orderTime) {
        return null;
    }
}
