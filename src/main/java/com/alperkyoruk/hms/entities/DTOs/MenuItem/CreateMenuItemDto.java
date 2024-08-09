package com.alperkyoruk.hms.entities.DTOs.MenuItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateMenuItemDto {
    private String name;
    private String description;
    private double price;
    private String category;
    private String status;
    private String dietaryInfo;
    private String ingredients;
    private String allergenInfo;
    private String caloricContent;
    private int preparationTime;
    private String availableTime;

}
