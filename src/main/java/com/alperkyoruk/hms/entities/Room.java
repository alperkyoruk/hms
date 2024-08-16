package com.alperkyoruk.hms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne(targetEntity = Reservation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    @JsonIgnore
    private Reservation reservation;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Guest> guests;


}
