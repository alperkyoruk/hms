package com.alperkyoruk.hms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

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

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "opening_hours")
    private Date openingHours;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "closing_hours")
    private Date closingHours;

    @Column(name = "contact_details")
    private String contactDetails;

    @Column(name = "usage_count")
    private int usageCount;

}
