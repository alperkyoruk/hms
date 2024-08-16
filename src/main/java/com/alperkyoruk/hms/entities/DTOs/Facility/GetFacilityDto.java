package com.alperkyoruk.hms.entities.DTOs.Facility;

import com.alperkyoruk.hms.entities.Facility;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFacilityDto {
    private int id;
    private String facilityName;
    private String facilityDescription;
    private double facilityPrice;
    private int facilityCapacity;
    private String facilityStatus;
    private String facilityType;
    private boolean reservationRequired;
    private String location;

    @JsonFormat(pattern = "HH:mm")
    private Date openingHours;

    @JsonFormat(pattern = "HH:mm")
    private Date closingHours;

    private String contactDetails;
    private int usageCount;

    public GetFacilityDto(Facility facility){
        this.id = facility.getId();
        this.facilityName = facility.getFacilityName();
        this.facilityDescription = facility.getFacilityDescription();
        this.facilityPrice = facility.getFacilityPrice();
        this.facilityCapacity = facility.getFacilityCapacity();
        this.facilityStatus = facility.getFacilityStatus();
        this.facilityType = facility.getFacilityType();
        this.reservationRequired = facility.isReservationRequired();
        this.location = facility.getLocation();
        this.openingHours = facility.getOpeningHours();
        this.closingHours = facility.getClosingHours();
        this.contactDetails = facility.getContactDetails();
        this.usageCount = facility.getUsageCount();
    }


    public List<GetFacilityDto> buildListGetFacilityDto(List<Facility> facilities) {
        return facilities.stream()
                .map(GetFacilityDto::new)
                .collect(Collectors.toList());
    }



}


