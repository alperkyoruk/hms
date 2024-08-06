package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Staff.CreateStaffDto;
import com.alperkyoruk.hms.entities.DTOs.Staff.GetStaffDto;

import java.util.List;

public interface StaffService {

    Result addStaff(CreateStaffDto createStaffDto);

    Result deleteStaff(int id);

    Result updateStaff(GetStaffDto getStaffDto);

    DataResult<GetStaffDto> getById(int id);

    DataResult<GetStaffDto> getByEmail(String email);

    DataResult<GetStaffDto> getByBadgeNumber(String badgeNumber);

    DataResult<GetStaffDto> getByPhoneNumber(String phoneNumber);

    DataResult<List<GetStaffDto>> getAllByFirstNameAndLastName(String firstName, String lastName);

    DataResult<List<GetStaffDto>> getAllByPosition(String position);

    DataResult<List<GetStaffDto>> getAllByDepartment(String department);

    DataResult<List<GetStaffDto>> getAllByStatus(String status);

    DataResult<List<GetStaffDto>> getAllByPerformanceRating(double performanceRating);

    DataResult<List<GetStaffDto>> getAllByPerformanceRatingDesc();

    DataResult<List<GetStaffDto>> getAll();



}
