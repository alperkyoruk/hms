package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.business.abstracts.MenuItemService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.business.abstracts.RoomServiceOrderService;
import com.alperkyoruk.hms.business.constants.GuestMessages;
import com.alperkyoruk.hms.business.constants.RoomMessages;
import com.alperkyoruk.hms.business.constants.RoomServiceOrderMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.RoomServiceOrderDao;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.CreateRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder.GetRoomServiceOrderDto;
import com.alperkyoruk.hms.entities.MenuItem;
import com.alperkyoruk.hms.entities.RoomServiceOrder;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceOrderManager implements RoomServiceOrderService {



    private RoomServiceOrderDao roomServiceOrderDao;


    private MenuItemService menuItemService;


    private RoomService roomService;

    private GuestService guestService;

    public RoomServiceOrderManager(RoomServiceOrderDao roomServiceOrderDao, MenuItemService menuItemService, RoomService roomService, @Lazy GuestService guestService) {
        this.roomServiceOrderDao = roomServiceOrderDao;
        this.menuItemService = menuItemService;
        this.roomService = roomService;
        this.guestService = guestService;
    }


    @Override
    public Result addRoomServiceOrder(CreateRoomServiceOrderDto createRoomServiceOrderDto) {


     var menuItemResult = menuItemService.getMenuItemsByIds(createRoomServiceOrderDto.getMenuItemIds());
        if(!menuItemResult.isSuccess()){
            return menuItemResult;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        var guest = guestService.getGuestByEmail(currentUser);
        if(!guest.isSuccess()){
            return new ErrorResult(GuestMessages.guestNotFound);
        }

        var room = guest.getData().getRoom();
        if(room == null){
            return new ErrorResult(RoomMessages.RoomsNotFound);
        }







        RoomServiceOrder roomServiceOrder = RoomServiceOrder.builder()
                .orderDate(new Date())
                .orderTime(new Date())
                .guest(guest.getData())
                .comment(createRoomServiceOrderDto.getComment())
                .menuItems(menuItemResult.getData())
                .room(room)
                .status(createRoomServiceOrderDto.getStatus())
                .totalPrice(menuItemResult.getData().stream().mapToDouble(MenuItem::getPrice).sum())
                .build();

        roomServiceOrderDao.save(roomServiceOrder);

        CalculateEstimatedTime(roomServiceOrder.getId());
        return new SuccessResult(RoomServiceOrderMessages.roomServiceOrderAddedSuccessfully);
    }

    @Override
    public Result deleteRoomServiceOrder(int id) {
        var roomServiceOrderResponse = roomServiceOrderDao.findById(id);
        if(roomServiceOrderResponse == null){
            return new ErrorResult(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        roomServiceOrderDao.delete(roomServiceOrderResponse);
        return new SuccessResult(RoomServiceOrderMessages.roomServiceOrderDeletedSuccessfully);
    }

    @Override
    public Result updateRoomServiceOrder(GetRoomServiceOrderDto getRoomServiceOrderDto) {
        var result = roomServiceOrderDao.findById(getRoomServiceOrderDto.getId());
        if(result == null){
            return new ErrorResult(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }

        var room = roomService.getRoomByRoomNumber(getRoomServiceOrderDto.getRoomNumber());
        if(room == null){
            return new ErrorResult(RoomMessages.RoomsNotFound);
        }

        var guest = result.getGuest();




        result.setComment(getRoomServiceOrderDto.getComment() == null ? result.getComment() : getRoomServiceOrderDto.getComment());
        result.setDeliveryTime(getRoomServiceOrderDto.getDeliveryTime() == null ? result.getDeliveryTime() : getRoomServiceOrderDto.getDeliveryTime());
        result.setOrderTime(getRoomServiceOrderDto.getOrderTime() == null ? result.getOrderTime() : getRoomServiceOrderDto.getOrderTime());
        result.setOrderDate(getRoomServiceOrderDto.getOrderDate() == null ? result.getOrderDate() : getRoomServiceOrderDto.getOrderDate());
        result.setStatus(getRoomServiceOrderDto.getStatus() == null ? result.getStatus() : getRoomServiceOrderDto.getStatus());
        result.setTotalPrice(getRoomServiceOrderDto.getTotalPrice() == 0 ? result.getTotalPrice() : getRoomServiceOrderDto.getTotalPrice());
        result.setGuest(guest == null ? result.getGuest() : guest);


        roomServiceOrderDao.save(result);
        return new SuccessResult(RoomServiceOrderMessages.roomServiceOrderUpdatedSuccessfully);
    }

    @Override
    public DataResult<GetRoomServiceOrderDto> getById(int id) {
        var result = roomServiceOrderDao.findById(id);
        if(result == null){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        var returnRoomServiceOrder = new GetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnRoomServiceOrder, RoomServiceOrderMessages.roomServiceOrderSuccessfullyBrought);
    }

    @Override
    public DataResult<GetRoomServiceOrderDto> getByRoomServiceOrderId(int roomServiceOrderId) {
        var result = roomServiceOrderDao.findById(roomServiceOrderId);
        if(result == null){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        var returnRoomServiceOrder = new GetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnRoomServiceOrder, RoomServiceOrderMessages.roomServiceOrderSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderStatus(String roomServiceOrderStatus) {
        var result = roomServiceOrderDao.findAllByStatus(roomServiceOrderStatus);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByRoomServiceOrderRoomNumber(int roomNumber) {
        var result = roomServiceOrderDao.findAllByRoomRoomNumber(roomNumber);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByGuestId(int guestId) {
        var result = roomServiceOrderDao.findAllByGuestId(guestId);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeBefore(LocalTime orderTime) {
        var result = roomServiceOrderDao.findAllByOrderTimeBefore(orderTime);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByOrderTimeAfter(LocalTime orderTime) {
        var result = roomServiceOrderDao.findAllByOrderTimeAfter(orderTime);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAll() {
        var result = roomServiceOrderDao.findAll();
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }
        result.forEach(roomServiceOrder -> roomServiceOrder.getMenuItems().size());

        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByDate(Date date) {
        var result = roomServiceOrderDao.findAllByOrderDate(date);
        if(result.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }

        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(result);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetRoomServiceOrderDto>> getAllByGuest() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        var orders = roomServiceOrderDao.findAllByGuestEmail(currentUser);
        if(orders.isEmpty()){
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }

        List<GetRoomServiceOrderDto> returnList = GetRoomServiceOrderDto.buildListGetRoomServiceOrderDto(orders);
        return new SuccessDataResult<>(returnList, RoomServiceOrderMessages.roomServiceOrdersSuccessfullyBrought);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    //delete all orders that are older than 30 days
    public void deleteAllByOrderDateBefore() {
        Date date30DaysAgo = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000); // 30 days ago

        var pastOrders = roomServiceOrderDao.findAllByOrderDate(date30DaysAgo);
        if(pastOrders.isEmpty()){
            return;
        }
        roomServiceOrderDao.deleteAll(pastOrders);
    }

    @Override
    public DataResult<GetRoomServiceOrderDto> CalculateEstimatedTime(int id) {
        RoomServiceOrder roomServiceOrder = roomServiceOrderDao.findById(id);
        if (roomServiceOrder == null) {
            return new ErrorDataResult<>(RoomServiceOrderMessages.roomServiceOrdersNotFound);
        }

        List<MenuItem> menuItems = roomServiceOrder.getMenuItems();

        int totalPreparationTimeInMinutes = menuItems.stream()
                .mapToInt(MenuItem::getPreparationTime)
                .sum();

        // Get the order time
        Date orderTime = roomServiceOrder.getOrderTime();

        // Calculate the estimated time by adding the total preparation time to the order time
        long estimatedTimeInMillis = orderTime.getTime() + ((long) totalPreparationTimeInMinutes * 60 * 1000);

        // Create a new Date object for the estimated time
        Date estimatedTime = new Date(estimatedTimeInMillis);

        roomServiceOrder.setEstimatedTime(estimatedTime);

        roomServiceOrderDao.save(roomServiceOrder);



        return new SuccessDataResult<>(RoomServiceOrderMessages.estimatedTimeCalculatedSuccessfully);
    }


}
