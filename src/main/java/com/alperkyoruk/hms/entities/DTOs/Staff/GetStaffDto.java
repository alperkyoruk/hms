package com.alperkyoruk.hms.entities.DTOs.Staff;

import com.alperkyoruk.hms.entities.Role;
import com.alperkyoruk.hms.entities.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetStaffDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String department;
    private String status;
    private Date hireDate;

    public GetStaffDto(Staff staff){
        this.id = staff.getId();
        this.firstName = staff.getFirstName();
        this.lastName = staff.getLastName();
        this.email = staff.getEmail();
        this.phoneNumber = staff.getPhoneNumber();
        this.position = staff.getPosition();
        this.department = staff.getDepartment();
        this.status = staff.getStatus();
        this.hireDate = staff.getHireDate();
    }

    public List<GetStaffDto> buildListGetStaffDto(List<Staff> staffs){
        return staffs.stream()
                .map(GetStaffDto::new)
                .collect(Collectors.toList());
    }

}


