package com.alperkyoruk.hms.entities.DTOs.RoomServiceOrder;

import com.alperkyoruk.hms.entities.DTOs.Guest.GetGuestDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateRoomServiceOrderDto {
    private String status;
    private String comment;
    private Date orderDate;
    private LocalTime estimatedTime;
    private LocalTime orderTime;
    private LocalTime deliveryTime;
    private double totalPrice;
    private int roomId;
    private int guestId;

}
