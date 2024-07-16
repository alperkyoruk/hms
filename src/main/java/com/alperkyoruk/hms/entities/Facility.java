package com.alperkyoruk.hms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "facility_description")
    private String facilityDescription;

    @Column(name = "facility_price")
    private double facilityPrice;

    @Column(name = "facility_capacity")
    private int facilityCapacity;

    @Column(name = "facility_status")
    private String facilityStatus;

    @Column(name = "facility_type")
    private String facilityType;

    @Column(name = "reservation_required")
    private boolean reservationRequired;

    @Column(name = "location")
    private String location;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "contact_details")
    private String contactDetails;

    @Column(name = "usage_stats")
    private String usageStats;
}
