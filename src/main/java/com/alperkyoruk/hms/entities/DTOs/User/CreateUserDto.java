package com.alperkyoruk.hms.entities.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserDto {
    private String badgeNumber;
    private String email;
    private String guestPhoneNumber;
    private String staffPhoneNumber;


}
