package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.MenuItemService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.CreateMenuItemDto;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.GetMenuItemDto;
import com.alperkyoruk.hms.entities.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menuItems")
public class MenuItemController {

    private MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService){
        this.menuItemService = menuItemService;
    }

    @PostMapping("/addMenuItem")
    public Result addMenuItem(@RequestBody CreateMenuItemDto createMenuItemDto){
        return menuItemService.addMenuItem(createMenuItemDto);
    }

    @PostMapping("/deleteMenuItem")
    public Result deleteMenuItem(@RequestBody int menuItemId){
        return menuItemService.deleteMenuItem(menuItemId);
    }

    @PostMapping("/updateMenuItem")
    public Result updateMenuItem(@RequestBody GetMenuItemDto getMenuItemDto){
        return menuItemService.updateMenuItem(getMenuItemDto);
    }

    @GetMapping("/getMenuItemById")
    public DataResult<MenuItem> getMenuItemById(@RequestBody int menuItemId){
        return menuItemService.getMenuItemById(menuItemId);
    }

    @GetMapping("/getMenuItems")
    public DataResult<List<GetMenuItemDto>> getMenuItems(){
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/getMenuItemsByName")
    public DataResult<List<GetMenuItemDto>> getMenuItemsByName(@RequestParam String name){
        return menuItemService.getMenuItemsByName(name);
    }

    @GetMapping("/getMenuItemsByCategory")
    public DataResult<List<GetMenuItemDto>> getMenuItemsByCategory(@RequestParam String category){
        return menuItemService.getMenuItemsByCategory(category);
    }

    @GetMapping("/getMenuItemsByPrice")
    public DataResult<List<GetMenuItemDto>> getMenuItemsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice){
        return menuItemService.getMenuItemsByPrice(minPrice, maxPrice);
    }

    @GetMapping("/getMenuItemsByStatus")
    public DataResult<List<GetMenuItemDto>> getMenuItemsByStatus(@RequestParam String status){
        return menuItemService.getMenuItemsByStatus(status);
    }

    @GetMapping("/getById")
    public DataResult<GetMenuItemDto> getById(@RequestParam int id){
        return menuItemService.getById(id);
    }

    @GetMapping("/getMenuItemsByIds")
    public DataResult<List<MenuItem>> getMenuItemsByIds(@RequestBody List<Integer> ids){
        return menuItemService.getMenuItemsByIds(ids);
    }



}
