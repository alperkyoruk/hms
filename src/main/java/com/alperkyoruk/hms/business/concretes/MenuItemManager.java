package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.MenuItemService;
import com.alperkyoruk.hms.business.constants.MenuItemMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.MenuItemDao;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.CreateMenuItemDto;
import com.alperkyoruk.hms.entities.DTOs.MenuItem.GetMenuItemDto;
import com.alperkyoruk.hms.entities.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuItemManager implements MenuItemService {


    private final MenuItemDao menuItemDao;

    @Override
    public Result addMenuItem(CreateMenuItemDto createMenuItemDto) {

        MenuItem menuItem = MenuItem.builder()
                .name(createMenuItemDto.getName())
                .price(createMenuItemDto.getPrice())
                .category(createMenuItemDto.getCategory())
                .status(createMenuItemDto.getStatus())
                .allergenInfo(createMenuItemDto.getAllergenInfo())
                .availableTime(createMenuItemDto.getAvailableTime())
                .caloricContent(createMenuItemDto.getCaloricContent())
                .description(createMenuItemDto.getDescription())
                .dietaryInfo(createMenuItemDto.getDietaryInfo())
                .ingredients(createMenuItemDto.getIngredients())
                .preparationTime(createMenuItemDto.getPreparationTime())
                .build();

        menuItemDao.save(menuItem);
        return new SuccessResult(MenuItemMessages.menuItemAdded);
    }

    @Override
    public Result deleteMenuItem(int menuItemId) {
        var menuItemResponse = menuItemDao.findById(menuItemId);

        if(menuItemResponse == null){
            return new ErrorResult(MenuItemMessages.menuItemNotFound);
        }

        menuItemDao.delete(menuItemResponse);
        return new SuccessResult(MenuItemMessages.menuItemDeleted);
    }

    @Override
    public Result updateMenuItem(GetMenuItemDto getMenuItemDto) {
        var result = menuItemDao.findById(getMenuItemDto.getId());
        if(result == null){
            return new ErrorResult(MenuItemMessages.menuItemNotFound);
        }

        result.setAllergenInfo(getMenuItemDto.getAllergenInfo() == null ? result.getAllergenInfo() : getMenuItemDto.getAllergenInfo());
        result.setAvailableTime(getMenuItemDto.getAvailableTime() == null ? result.getAvailableTime() : getMenuItemDto.getAvailableTime());
        result.setCaloricContent(getMenuItemDto.getCaloricContent() == null ? result.getCaloricContent() : getMenuItemDto.getCaloricContent());
        result.setCategory(getMenuItemDto.getCategory() == null ? result.getCategory() : getMenuItemDto.getCategory());
        result.setDescription(getMenuItemDto.getDescription() == null ? result.getDescription() : getMenuItemDto.getDescription());
        result.setDietaryInfo(getMenuItemDto.getDietaryInfo() == null ? result.getDietaryInfo() : getMenuItemDto.getDietaryInfo());
        result.setIngredients(getMenuItemDto.getIngredients() == null ? result.getIngredients() : getMenuItemDto.getIngredients());
        result.setName(getMenuItemDto.getName() == null ? result.getName() : getMenuItemDto.getName());
        result.setPreparationTime(getMenuItemDto.getPreparationTime() == 0 ? result.getPreparationTime() : getMenuItemDto.getPreparationTime());
        result.setPrice(getMenuItemDto.getPrice() == 0 ? result.getPrice() : getMenuItemDto.getPrice());
        result.setStatus(getMenuItemDto.getStatus() == null ? result.getStatus() : getMenuItemDto.getStatus());
        result.setAvailableTime(getMenuItemDto.getAvailableTime() == null ? result.getAvailableTime() : getMenuItemDto.getAvailableTime());

        menuItemDao.save(result);
        return new SuccessResult(MenuItemMessages.menuItemUpdated);
    }

    @Override
    public DataResult<List<GetMenuItemDto>> getAllMenuItems() {

        var result = menuItemDao.findAll();

        if(result.isEmpty()){
            return new ErrorDataResult<>(MenuItemMessages.menuItemsNotFound);
        }

        List<GetMenuItemDto> returnList = new GetMenuItemDto().buildListGetMenuItemDto(result);
        return new SuccessDataResult<>(returnList, MenuItemMessages.menuItemsSuccessfullyBrought);
    }

    @Override
    public DataResult<GetMenuItemDto> getById(int menuItemId) {
        var result = menuItemDao.findById(menuItemId);

        if(result == null){
            return new ErrorDataResult<>(MenuItemMessages.menuItemNotFound);
        }

        var returnMenuItem = new GetMenuItemDto(result);
        return new SuccessDataResult<>(returnMenuItem, MenuItemMessages.menuItemSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetMenuItemDto>> getMenuItemsByName(String name) {
        var result = menuItemDao.findAllByNameContainsIgnoreCase(name);

        if(result.isEmpty()){
            return new ErrorDataResult<>(MenuItemMessages.menuItemsNotFound);
        }

        List<GetMenuItemDto> returnList = new GetMenuItemDto().buildListGetMenuItemDto(result);
        return new SuccessDataResult<>(returnList, MenuItemMessages.menuItemsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetMenuItemDto>> getMenuItemsByPrice(double minPrice, double maxPrice) {
        var result = menuItemDao.findAllByPriceBetween(minPrice, maxPrice);

        if(result.isEmpty()){
            return new ErrorDataResult<>(MenuItemMessages.menuItemsNotFound);
        }

        List<GetMenuItemDto> returnList = new GetMenuItemDto().buildListGetMenuItemDto(result);
        return new SuccessDataResult<>(returnList, MenuItemMessages.menuItemsSuccessfullyBrought);
    }


    @Override
    public DataResult<List<GetMenuItemDto>> getMenuItemsByCategory(String category) {
        var result = menuItemDao.findAllByCategory(category);

        if(result.isEmpty()){
            return new ErrorDataResult<>(MenuItemMessages.menuItemsNotFound);
        }

        List<GetMenuItemDto> returnList = new GetMenuItemDto().buildListGetMenuItemDto(result);
        return new SuccessDataResult<>(returnList, MenuItemMessages.menuItemsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetMenuItemDto>> getMenuItemsByStatus(String status) {
        var result = menuItemDao.findAllByStatus(status);

        if(result.isEmpty()){
            return new ErrorDataResult<>(MenuItemMessages.menuItemsNotFound);
        }

        List<GetMenuItemDto> returnList = new GetMenuItemDto().buildListGetMenuItemDto(result);
        return new SuccessDataResult<>(returnList, MenuItemMessages.menuItemsSuccessfullyBrought);
    }

    @Override
    public DataResult<MenuItem> getMenuItemById(int menuItemId) {
        var result = menuItemDao.findById(menuItemId);
        if(result == null){
            return new ErrorDataResult<>(MenuItemMessages.menuItemNotFound);
        }

        return new SuccessDataResult<>(result, MenuItemMessages.menuItemSuccessfullyBrought);
    }
}
