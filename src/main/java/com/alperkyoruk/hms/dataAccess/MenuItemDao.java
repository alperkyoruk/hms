package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemDao extends JpaRepository<MenuItem, Integer> {
    MenuItem findById(int id);

    List<MenuItem> findAllByNameContainsIgnoreCase(String name);

    List<MenuItem> findAllByPriceBetween(double minPrice, double maxPrice);

    List<MenuItem> findAllByCategory(String category);

    List<MenuItem> findAllByStatus(String status);

    List<MenuItem> findAllByRoomServiceOrderId(int roomServiceOrderId);

    List<MenuItem> findAllByIdIn(List<Integer> ids);
}
