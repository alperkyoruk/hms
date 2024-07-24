package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.FacilityService;
import com.alperkyoruk.hms.business.constants.FacilityMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.FacilityDao;
import com.alperkyoruk.hms.entities.DTOs.Facility.CreateFacilityDto;
import com.alperkyoruk.hms.entities.DTOs.Facility.GetFacilityDto;
import com.alperkyoruk.hms.entities.Facility;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FacilityManager implements FacilityService {

    private FacilityDao facilityDao;

    public FacilityManager(FacilityDao facilityDao) {
        this.facilityDao = facilityDao;
    }

    @Override
    public Result addFacility(CreateFacilityDto facilityDto) {
        Facility facility = Facility.builder()
                .facilityName(facilityDto.getFacilityName())
                .facilityType(facilityDto.getFacilityType())
                .facilityDescription(facilityDto.getFacilityDescription())
                .facilityStatus(facilityDto.getFacilityStatus())
                .facilityPrice(facilityDto.getFacilityPrice())
                .facilityCapacity(facilityDto.getFacilityCapacity())
                .reservationRequired(facilityDto.isReservationRequired())
                .location(facilityDto.getLocation())
                .openingHours(facilityDto.getOpeningHours())
                .closingHours(facilityDto.getClosingHours())
                .contactDetails(facilityDto.getContactDetails())
                .usageCount(0)
                .build();

        facilityDao.save(facility);

        return new SuccessResult(FacilityMessages.FacilityAdded);
    }

    @Override
    public Result deleteFacility(int facilityId) {
        var facilityResponse = facilityDao.findById(facilityId);

        if(facilityResponse == null){
            return new ErrorResult(FacilityMessages.FacilityNotFound);
        }

        facilityDao.deleteById(facilityId);

        return new SuccessResult(FacilityMessages.FacilityDeleted);
    }

    @Override
    public Result updateFacility(GetFacilityDto facilityDto) {
        var facilityResponse = facilityDao.findById(facilityDto.getId());

        if(facilityResponse == null){
            return new ErrorResult(FacilityMessages.FacilityNotFound);
        }

        facilityResponse.setFacilityCapacity(facilityDto.getFacilityCapacity() == 0 ? facilityResponse.getFacilityCapacity() : facilityDto.getFacilityCapacity());
        facilityResponse.setFacilityDescription(facilityDto.getFacilityDescription() == null ? facilityResponse.getFacilityDescription() : facilityDto.getFacilityDescription());
        facilityResponse.setFacilityName(facilityDto.getFacilityName() == null ? facilityResponse.getFacilityName() : facilityDto.getFacilityName());
        facilityResponse.setFacilityPrice(facilityDto.getFacilityPrice() == 0 ? facilityResponse.getFacilityPrice() : facilityDto.getFacilityPrice());
        facilityResponse.setFacilityStatus(facilityDto.getFacilityStatus() == null ? facilityResponse.getFacilityStatus() : facilityDto.getFacilityStatus());
        facilityResponse.setFacilityType(facilityDto.getFacilityType() == null ? facilityResponse.getFacilityType() : facilityDto.getFacilityType());
        facilityResponse.setFacilityPrice(facilityDto.getFacilityPrice() == 0 ? facilityResponse.getFacilityPrice() : facilityDto.getFacilityPrice());
        facilityResponse.setReservationRequired(facilityDto.isReservationRequired());
        facilityResponse.setLocation(facilityDto.getLocation() == null ? facilityResponse.getLocation() : facilityDto.getLocation());
        facilityResponse.setOpeningHours(facilityDto.getOpeningHours() == null ? facilityResponse.getOpeningHours() : facilityDto.getOpeningHours());
        facilityResponse.setClosingHours(facilityDto.getClosingHours() == null ? facilityResponse.getClosingHours() : facilityDto.getClosingHours());
        facilityResponse.setContactDetails(facilityDto.getContactDetails() == null ? facilityResponse.getContactDetails() : facilityDto.getContactDetails());

        facilityDao.save(facilityResponse);

        return new SuccessResult(FacilityMessages.FacilityUpdated);
    }

    @Override
    public DataResult<List<GetFacilityDto>> getAllFacilities() {

        var facilityList = facilityDao.findAll();

        if(facilityList.isEmpty()){
            return new ErrorDataResult<>(FacilityMessages.FacilitiesNotFound);
        }

        List<GetFacilityDto> returnList = new GetFacilityDto().buildListGetFacilityDto(facilityList);
        return new SuccessDataResult<>(returnList, FacilityMessages.FacilitiesSuccessfullyBrought);
    }

    @Override
    public DataResult<GetFacilityDto> getFacilityById(int facilityId) {
        var facility = facilityDao.findById(facilityId);

        if(facility == null){
            return new ErrorDataResult<>(FacilityMessages.FacilityNotFound);
        }

        var returnFacility = new GetFacilityDto(facility);

        return new SuccessDataResult<>(returnFacility, FacilityMessages.FacilitySuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetFacilityDto>> getFacilitiesByType(String type) {
        var facilityList = facilityDao.findAllByFacilityType(type);

        if(facilityList.isEmpty()){
            return new ErrorDataResult<>(FacilityMessages.FacilitiesNotFound);
        }

        List<GetFacilityDto> returnList = new GetFacilityDto().buildListGetFacilityDto(facilityList);
        return new SuccessDataResult<>(returnList, FacilityMessages.FacilitiesSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetFacilityDto>> getFacilitiesByStatus(String status) {
        var facilityList = facilityDao.findAllByFacilityStatus(status);

        if(facilityList.isEmpty()){
            return new ErrorDataResult<>(FacilityMessages.FacilitiesNotFound);
        }

        List<GetFacilityDto> returnList = new GetFacilityDto().buildListGetFacilityDto(facilityList);

        return new SuccessDataResult<>(returnList, FacilityMessages.FacilitiesSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetFacilityDto>> getFacilitiesByOpeningHours(LocalTime openingHour, LocalTime closingHour) {

        var facilityList = facilityDao.findAllByOpeningHoursBeforeAndClosingHoursAfter(openingHour, closingHour);

        if(facilityList.isEmpty()){
            return new ErrorDataResult<>(FacilityMessages.FacilitiesNotFound);
        }

        List<GetFacilityDto> returnList = new GetFacilityDto().buildListGetFacilityDto(facilityList);

        return new SuccessDataResult<>(returnList, FacilityMessages.FacilitiesSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetFacilityDto>> getFacilitiesByPriceBefore(double price) {
        var facilityList = facilityDao.findAllByFacilityPriceBefore(price);

        if(facilityList.isEmpty()){
            return new ErrorDataResult<>(FacilityMessages.FacilitiesNotFound);
        }

        List<GetFacilityDto> returnList = new GetFacilityDto().buildListGetFacilityDto(facilityList);

        return new SuccessDataResult<>(returnList, FacilityMessages.FacilitiesSuccessfullyBrought);
    }
}
