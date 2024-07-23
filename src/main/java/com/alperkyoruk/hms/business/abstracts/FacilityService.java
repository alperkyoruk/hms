package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Facility.CreateFacilityDto;
import com.alperkyoruk.hms.entities.DTOs.Facility.GetFacilityDto;

import java.util.List;

public interface FacilityService {

    Result addFacility(CreateFacilityDto facilityDto);

    Result deleteFacility(int facilityId);

    Result updateFacility(GetFacilityDto facilityDto);

    DataResult<List<GetFacilityDto>> getAllFacilities();

    DataResult<GetFacilityDto> getFacilityById(int facilityId);

    DataResult<List<GetFacilityDto>> getFacilitiesByType(String type);

    DataResult<List<GetFacilityDto>> getFacilitiesByStatus(String status);

    DataResult<List<GetFacilityDto>> getFacilitiesByOpeningHours(String openingHour, String closingHour);

    DataResult<List<GetFacilityDto>> getFacilitiesByPrice(double minPrice, double maxPrice);



}
