package com.alperkyoruk.hms.entities.DTOs.Metrics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMetricsDto {
    private double occupancyRate;
    private int guestCount;
    private int availableRoomCount;
    private int totalRoomCount;
    private int revenue;



}
