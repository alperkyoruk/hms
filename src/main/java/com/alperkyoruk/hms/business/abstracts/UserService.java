package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.Result;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.alperkyoruk.hms.entities.DTOs.User.CreateUserDto;
import com.alperkyoruk.hms.entities.User;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.entities.DTOs.User.GetUserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    Result addUser(CreateUserDto requestUserDto);

    Result deleteUser(int userId);

    Result updateUser(GetUserDto requestUserDto);

    DataResult<User> getUserById(int userId);

    DataResult<User> getUserByUsername(String username);

    DataResult<List<User>> getUsers();

    //DataResult<List<User>> getUsersByRole(String role);

    Result addModerator(int userId);

    Result removeModerator(int userId);

    Result addStaff(int userId);

    Result removeStaff(int userId);

    Result addGuest(String email, String phoneNumber);




}
