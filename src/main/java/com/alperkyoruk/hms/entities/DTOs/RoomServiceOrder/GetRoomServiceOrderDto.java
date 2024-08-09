package com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder;

import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.GetMenuItemDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import com.alperkyoruk.hms.entities.RoomServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRoomServiceOrderDto {
    private int id;
    private String status;
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date orderDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date estimatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date orderTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date deliveryTime;
    private double totalPrice;
    private GetRoomDto room;

    @JsonIgnore
    private GetGuestDto guest;
    private List<GetMenuItemDto> menuItems;

    public GetRoomServiceOrderDto(RoomServiceOrder roomServiceOrder) {
        this.id = roomServiceOrder.getId();
        this.status = roomServiceOrder.getStatus();
        this.comment = roomServiceOrder.getComment();
        this.orderDate = roomServiceOrder.getOrderDate();
        this.estimatedTime = roomServiceOrder.getEstimatedTime();
        this.orderTime = roomServiceOrder.getOrderTime();
        this.deliveryTime = roomServiceOrder.getDeliveryTime();
        this.totalPrice = roomServiceOrder.getTotalPrice();
        this.room = new GetRoomDto(roomServiceOrder.getRoom());
        this.guest = new GetGuestDto(roomServiceOrder.getGuest());
        this.menuItems = GetMenuItemDto.buildListGetMenuItemDto(roomServiceOrder.getMenuItems());
    }

    public static List<GetRoomServiceOrderDto> buildListGetRoomServiceOrderDto(List<RoomServiceOrder> roomServiceOrders) {
        return roomServiceOrders.stream()
                .map(GetRoomServiceOrderDto::new)
                .collect(Collectors.toList());
    }


}
