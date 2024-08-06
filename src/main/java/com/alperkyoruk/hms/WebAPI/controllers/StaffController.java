package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.StaffService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Staff.CreateStaffDto;
import com.alperkyoruk.hms.entities.DTOs.Staff.GetStaffDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private StaffService staffService;

    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }


    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody CreateStaffDto createStaffDto){
        return staffService.addStaff(createStaffDto);
    }

    @PostMapping("/deleteStaff")
    public Result deleteStaff(@RequestBody int staffId){
        return staffService.deleteStaff(staffId);
    }

    @PostMapping("/updateStaff")
    public Result updateStaff(@RequestBody GetStaffDto getStaffDto){
        return staffService.updateStaff(getStaffDto);
    }

    @GetMapping("/getById")
    public DataResult<GetStaffDto> getById(@RequestParam int staffId){
        return staffService.getById(staffId);
    }

    @GetMapping("/getAllStaff")
    public DataResult<List<GetStaffDto>> getAll(){
        return staffService.getAll();
    }

    @GetMapping("/getStaffByDepartment")
    public DataResult<List<GetStaffDto>> getStaffByDepartment(@RequestParam String department){
        return staffService.getAllByDepartment(department);
    }

    @GetMapping("/getStaffByEmail")
    public DataResult<GetStaffDto> getStaffByEmail(@RequestParam String email){
        return staffService.getByEmail(email);
    }

    @GetMapping("/getStaffByPhoneNumber")
    public DataResult<GetStaffDto> getStaffByPhoneNumber(@RequestParam String phoneNumber){
        return staffService.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getStaffByBadgeNumber")
    public DataResult<GetStaffDto> getStaffByBadgeNumber(@RequestParam String badgeNumber){
        return staffService.getByBadgeNumber(badgeNumber);
    }

    @GetMapping("/getStaffByPosition")
    public DataResult<List<GetStaffDto>> getStaffByPosition(@RequestParam String position){
        return staffService.getAllByPosition(position);
    }

    @GetMapping("/getStaffByStatus")
    public DataResult<List<GetStaffDto>> getStaffByStatus(@RequestParam String status){
        return staffService.getAllByStatus(status);
    }

    @GetMapping("/getStaffByPerformanceRating")
    public DataResult<List<GetStaffDto>> getStaffByPerformanceRating(@RequestParam double performanceRating){
        return staffService.getAllByPerformanceRating(performanceRating);
    }

    @GetMapping("/getStaffByFirstNameAndLastName")
    public DataResult<List<GetStaffDto>> getStaffByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        return staffService.getAllByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/getStaffByPerformanceRatingDesc")
    public DataResult<List<GetStaffDto>> getStaffByPerformanceRatingDesc(){
        return staffService.getAllByPerformanceRatingDesc();
    }






}
