package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.CreateMenuItemDto;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.GetMenuItemDto;

import java.util.List;

public interface MenuItemService {
    Result addMenuItem(CreateMenuItemDto createMenuItemDto);

    Result deleteMenuItem(int menuItemId);

    Result updateMenuItem(GetMenuItemDto getMenuItemDto);

    DataResult<List<GetMenuItemDto>> getAllMenuItems();

    DataResult<GetMenuItemDto> getMenuItemById(int menuItemId);

    DataResult<List<GetMenuItemDto>> getMenuItemsByName(String name);

    DataResult<List<GetMenuItemDto>> getMenuItemsByPriceBefore(double price);

    DataResult<List<GetMenuItemDto>> getMenuItemsByPriceAfter(double price);

    DataResult<List<GetMenuItemDto>> getMenuItemsByCategory(String category);

    DataResult<List<GetMenuItemDto>> getMenuItemsByStatus(String status);


 }
