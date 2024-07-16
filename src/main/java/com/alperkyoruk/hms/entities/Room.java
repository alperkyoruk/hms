package com.alperkyoruk.hms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "rooms")
public class Room {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "room_status")
    private String roomStatus;

    @Column(name = "room_price")
    private double roomPrice;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @Column(name = "bed_type")
    private String bedType;

}
