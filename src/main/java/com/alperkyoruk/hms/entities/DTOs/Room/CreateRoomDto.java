package com.alperkyoruk.hms.entities.DTOs.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateRoomDto {
    private String roomType;
    private int roomNumber;
    private String roomDescription;
    private double roomPrice;
    private int roomCapacity;
    private String roomStatus;
    private String bedType;


}
