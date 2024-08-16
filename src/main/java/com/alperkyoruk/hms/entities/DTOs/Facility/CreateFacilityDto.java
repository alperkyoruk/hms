package com.alperkyoruk.hms.entities.DTOs.Facility;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

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

        @JsonFormat(pattern = "HH:mm")
        private Date openingHours;

        @JsonFormat(pattern = "HH:mm")
        private Date closingHours;

        private String contactDetails;

        private int usageCount;
}
