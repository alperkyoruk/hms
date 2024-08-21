package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.GuestService;
import com.alperkyoruk.hms.business.abstracts.MetricService;
import com.alperkyoruk.hms.business.abstracts.RoomService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.SuccessDataResult;
import com.alperkyoruk.hms.entities.DTOs.Metrics.GetMetricsDto;
import com.alperkyoruk.hms.entities.DTOs.Room.GetRoomDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MetricManager implements MetricService {

    private RoomService roomService;
    private GuestService guestService;

    public MetricManager(@Lazy RoomService roomService,@Lazy GuestService guestService){
        this.roomService = roomService;
        this.guestService = guestService;
    }


    @Override
    public DataResult<GetMetricsDto> getMetrics() {
        var guests = guestService.getAllGuests().getData();
        var guestCount = guests.size();
        var activeGuestCount = (int) guests.stream().filter(guest -> guest.getStatus().equals("ACTIVE")).count();
        var rooms = roomService.getAll().getData();
        var totalRoomCount = rooms.size();
        var maxGuestCount = rooms.stream().mapToInt(GetRoomDto::getRoomCapacity).sum();
        var availableRoomCount = (int) rooms.stream().filter(room -> room.getRoomStatus().equals("ACTIVE")).count();
        double occupancyRate = (((double) (totalRoomCount - availableRoomCount) / totalRoomCount) * 100);

        GetMetricsDto getMetricsDto = new GetMetricsDto();
        getMetricsDto.setGuestCount(guestCount);
        getMetricsDto.setTotalRoomCount(totalRoomCount);
        getMetricsDto.setAvailableRoomCount(availableRoomCount);
        getMetricsDto.setOccupancyRate(occupancyRate);

        return new SuccessDataResult<>(getMetricsDto, "Metrics retrieved successfully");
    }
}
