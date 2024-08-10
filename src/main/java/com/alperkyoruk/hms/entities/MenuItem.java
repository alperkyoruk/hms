package com.alperkyoruk.hms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "category")
    private String category;

    @Column(name = "status")
    private String status;

    @Column(name = "dietary_info")
    private String dietaryInfo;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "caloric_content")
    private String caloricContent;

    @Column(name = "allergen_info")
    private String allergenInfo;

    @Column(name = "preparation_time")
    private int preparationTime;

    @Column(name = "avaliable_time")
    private String availableTime;

    @ManyToMany(targetEntity = RoomServiceOrder.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_service_order_items",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "room_service_order_id")
    )
    private List<RoomServiceOrder> roomServiceOrder;

    

}
