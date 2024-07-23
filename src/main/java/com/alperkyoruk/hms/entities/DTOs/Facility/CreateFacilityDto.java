package com.alperkyoruk.hms.entities.DTOs.Facility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateFacilityDto {

        private String facilityName;

        private String facilityDescription;

        private double facilityPrice;

        private int facilityCapacity;

        private String facilityStatus;

        private String facilityType;

        private boolean reservationRequired;

        private String location;

        private LocalTime openingHours;

        private LocalTime closingHours;

        private String contactDetails;

        private int usageCount;
}
