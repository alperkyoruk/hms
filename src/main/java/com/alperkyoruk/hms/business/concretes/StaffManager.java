package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.StaffService;
import com.alperkyoruk.hms.business.constants.StaffMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.StaffDao;
import com.alperkyoruk.hms.entities.DTOs.Staff.CreateStaffDto;
import com.alperkyoruk.hms.entities.DTOs.Staff.GetStaffDto;
import com.alperkyoruk.hms.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class StaffManager implements StaffService {

    @Autowired
    private StaffDao staffDao;


    @Override
    public Result addStaff(CreateStaffDto createStaffDto) {

        var departmentLetter = createStaffDto.getDepartment().toUpperCase().charAt(0);
        if(departmentLetter < 65 || departmentLetter > 90){
            return new ErrorResult(StaffMessages.DepartmentLetterError);
        }

        SecureRandom secureRandom = new SecureRandom();
        int number = secureRandom.nextInt(90000) + 10000; // 10000 to 99999
        String badgeNumber =  departmentLetter + String.valueOf(number);

        Staff staff = Staff.builder()
                .firstName(createStaffDto.getFirstName())
                .lastName(createStaffDto.getLastName())
                .email(createStaffDto.getEmail())
                .badgeNumber(createStaffDto.getBadgeNumber())
                .phoneNumber(createStaffDto.getPhoneNumber())
                .position(createStaffDto.getPosition())
                .department(createStaffDto.getDepartment())
                .status(createStaffDto.getStatus())
                .performanceRating(0)
                .hireDate(createStaffDto.getHireDate())
                .badgeNumber(badgeNumber)
                .build();

        staffDao.save(staff);
        return new SuccessResult(StaffMessages.StaffAddedSuccessfully);
    }

    @Override
    public Result deleteStaff(int id) {
        var staffResponse = staffDao.findById(id);
        if(staffResponse == null){
            return new ErrorResult(StaffMessages.StaffNotFound);
        }
        staffDao.deleteById(id);
        return new SuccessResult(StaffMessages.StaffDeletedSuccessfully);
    }

    @Override
    public Result updateStaff(GetStaffDto getStaffDto) {
        var staffResponse = staffDao.findById(getStaffDto.getId());
        if(staffResponse == null){
            return new ErrorResult(StaffMessages.StaffNotFound);
        }

        staffResponse.setFirstName(getStaffDto.getFirstName() == null ? staffResponse.getFirstName() : getStaffDto.getFirstName());
        staffResponse.setLastName(getStaffDto.getLastName() == null ? staffResponse.getLastName() : getStaffDto.getLastName());
        staffResponse.setEmail(getStaffDto.getEmail() == null ? staffResponse.getEmail() : getStaffDto.getEmail());
        staffResponse.setPhoneNumber(getStaffDto.getPhoneNumber() == null ? staffResponse.getPhoneNumber() : getStaffDto.getPhoneNumber());
        staffResponse.setPosition(getStaffDto.getPosition() == null ? staffResponse.getPosition() : getStaffDto.getPosition());
        staffResponse.setDepartment(getStaffDto.getDepartment() == null ? staffResponse.getDepartment() : getStaffDto.getDepartment());
        staffResponse.setStatus(getStaffDto.getStatus() == null ? staffResponse.getStatus() : getStaffDto.getStatus());
        staffResponse.setHireDate(getStaffDto.getHireDate() == null ? staffResponse.getHireDate() : getStaffDto.getHireDate());

        return new SuccessResult(StaffMessages.StaffUpdatedSuccessfully);
    }

    @Override
    public DataResult<GetStaffDto> getById(int id) {
        var staffResponse = staffDao.findById(id);
        if(staffResponse == null){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        var returnStaff = new GetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnStaff, StaffMessages.StaffSuccessfullyBrought);
    }

    @Override
    public DataResult<GetStaffDto> getByEmail(String email) {
        var staffResponse = staffDao.findByEmail(email);
        if(staffResponse == null){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        var returnStaff = new GetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnStaff, StaffMessages.StaffSuccessfullyBrought);
    }

    @Override
    public DataResult<GetStaffDto> getByBadgeNumber(String badgeNumber) {
        var staffResponse = staffDao.findByBadgeNumber(badgeNumber);
        if(staffResponse == null){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        var returnStaff = new GetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnStaff, StaffMessages.StaffSuccessfullyBrought);
    }

    @Override
    public DataResult<GetStaffDto> getByPhoneNumber(String phoneNumber) {
        var staffResponse = staffDao.findByPhoneNumber(phoneNumber);
        if(staffResponse == null){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        var returnStaff = new GetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnStaff, StaffMessages.StaffSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAllByFirstNameAndLastName(String firstName, String lastName) {
        var staffResponse = staffDao.findAllByFirstNameAndLastNameContainsIgnoreCase(firstName, lastName);
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAllByPosition(String position) {
        var staffResponse = staffDao.findAllByPosition(position);
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAllByDepartment(String department) {
        var staffResponse = staffDao.findAllByDepartment(department);
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAllByStatus(String status) {
        var staffResponse = staffDao.findAllByStatus(status);
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAllByPerformanceRating(double performanceRating) {
        var staffResponse = staffDao.findAllByPerformanceRatingGreaterThanEqual(performanceRating);
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetStaffDto>> getAll() {
        var staffResponse = staffDao.findAll();
        if(staffResponse.isEmpty()){
            return new ErrorDataResult<>(StaffMessages.StaffNotFound);
        }

        List<GetStaffDto> returnList = GetStaffDto.buildListGetStaffDto(staffResponse);
        return new SuccessDataResult<>(returnList, StaffMessages.StaffsSuccessfullyBrought);
    }

}
