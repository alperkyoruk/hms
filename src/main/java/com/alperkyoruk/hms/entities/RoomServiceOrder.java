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
@Table(name = "room_service_orders")
public class RoomServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "room_service_order")
    private List<MenuItem> items;

    private String status;

    private LocalTime estimatedTime;

    private LocalTime orderTime;

    private LocalTime deliveryTime;

    private String comment;

    private String totalPrice;

    @ManyToOne(targetEntity = Guest.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;


}
