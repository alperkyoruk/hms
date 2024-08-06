package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.FacilityService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Facility.CreateFacilityDto;
import com.alperkyoruk.hms.entities.DTOs.Facility.GetFacilityDto;
import com.alperkyoruk.hms.entities.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private FacilityService facilityService;

    public FacilityController(FacilityService facilityService){
        this.facilityService = facilityService;
    }


    @PostMapping("/addFacility")
    public Result addFacility(@RequestBody CreateFacilityDto createFacilityDto){
        return facilityService.addFacility(createFacilityDto);
    }

    @PostMapping("/deleteFacility")
    public Result deleteFacility(@RequestBody int facilityId){
        return facilityService.deleteFacility(facilityId);
    }

    @PostMapping("/updateFacility")
    public Result updateFacility(@RequestBody GetFacilityDto getFacilityDto){
        return facilityService.updateFacility(getFacilityDto);
    }

    @GetMapping("/getFacilityById")
    public DataResult<GetFacilityDto> getFacilityById(@RequestParam int facilityId){
        return facilityService.getFacilityById(facilityId);
    }

    @GetMapping("/getFacilities")
    public DataResult<List<GetFacilityDto>> getFacilities(){
        return facilityService.getAllFacilities();
    }

    @GetMapping("/getFacilitiesByType")
    public DataResult<List<GetFacilityDto>> getFacilitiesByType(@RequestParam String type){
        return facilityService.getFacilitiesByType(type);
    }

    @GetMapping("/getFacilitiesByStatus")
    public DataResult<List<GetFacilityDto>> getFacilitiesByStatus(@RequestParam String status){
        return facilityService.getFacilitiesByStatus(status);
    }

    @GetMapping("/getFacilitiesByOpeningHours")
    public DataResult<List<GetFacilityDto>> getFacilitiesByOpeningHours(@RequestParam LocalTime openingHour, @RequestParam LocalTime closingHour){
        return facilityService.getFacilitiesByOpeningHours(openingHour, closingHour);
    }

    @GetMapping("/getFacilitiesByPrice")
    public DataResult<List<GetFacilityDto>> getFacilitiesByPrice(@RequestParam double maxPrice){
        return facilityService.getFacilitiesByPriceBefore(maxPrice);
    }








}
