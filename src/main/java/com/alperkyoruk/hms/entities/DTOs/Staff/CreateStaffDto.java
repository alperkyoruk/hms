package com.alperkyoruk.hms.entities.DTOs.Staff;

import com.alperkyoruk.hms.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateStaffDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String password;
    private String department;
    private String status;
    private Date hireDate;
    private Set<Role> authorities;
    private String badgeNumber;

}
