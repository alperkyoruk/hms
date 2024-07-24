package com.alperkyoruk.hms.entities.DTOs.MenuItem;

import com.alperkyoruk.hms.entities.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMenuItemDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String status;
    private String dietaryInfo;
    private String ingredients;
    private String allergenInfo;
    private String caloricContent;
    private LocalTime preparationTime;
    private String availableTime;

    public GetMenuItemDto(MenuItem menuItem){
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.price = menuItem.getPrice();
        this.category = menuItem.getCategory();
        this.status = menuItem.getStatus();
        this.dietaryInfo = menuItem.getDietaryInfo();
        this.ingredients = menuItem.getIngredients();
        this.allergenInfo = menuItem.getAllergenInfo();
        this.caloricContent = menuItem.getCaloricContent();
        this.preparationTime = menuItem.getPreparationTime();
        this.availableTime = menuItem.getAvailableTime();
    }

    public List<GetMenuItemDto> buildListGetMenuItemDto(List<MenuItem> menuItems) {
        return menuItems.stream()
                .map(GetMenuItemDto::new)
                .collect(Collectors.toList());

    }

}
