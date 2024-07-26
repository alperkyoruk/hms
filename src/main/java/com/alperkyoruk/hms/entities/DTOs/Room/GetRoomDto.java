package com.alperkyoruk.hms.entities.DTOs.Room;

import com.alperkyoruk.hms.entities.DTOs.MenuItem.GetMenuItemDto;
import com.alperkyoruk.hms.entities.MenuItem;
import com.alperkyoruk.hms.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRoomDto {
    private int id;
    private String roomType;
    private int roomNumber;
    private String roomDescription;
    private double roomPrice;
    private int roomCapacity;
    private String roomStatus;
    private String bedType;


    public GetRoomDto(Room room) {
        this.id = room.getId();
        this.roomType = room.getRoomType();
        this.roomNumber = room.getRoomNumber();
        this.roomDescription = room.getRoomDescription();
        this.roomPrice = room.getRoomPrice();
        this.roomCapacity = room.getRoomCapacity();
        this.roomStatus = room.getRoomStatus();
        this.bedType = room.getBedType();
    }

    public static List<GetRoomDto> buildListGetRoomDto(List<Room> rooms) {
        return rooms.stream()
                .map(GetRoomDto::new)
                .collect(Collectors.toList());

    }

}
