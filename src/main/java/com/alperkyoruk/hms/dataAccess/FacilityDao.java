package com.alperkyoruk.hms.dataAccess;

import com.alperkyoruk.hms.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityDao extends JpaRepository<Facility, Integer> {
    Facility findById(int id);

    Facility findByFacilityName(String facilityName);

    List<Facility> findAllByFacilityPriceBefore(double facilityPrice);

    List<Facility> findAllByFacilityCapacityAfter(int facilityCapacity);

    List<Facility> findAllByFacilityStatus(String facilityStatus);

    List<Facility> findAllByOpeningHoursBetween(String openingHour, String closingHour);

    List<Facility> findAllByFacilityType(String facilityType);

}
