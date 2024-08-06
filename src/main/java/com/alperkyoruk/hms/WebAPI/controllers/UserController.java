package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.UserService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.User.CreateUserDto;
import com.alperkyoruk.hms.entities.DTOs.User.GetUserDto;
import com.alperkyoruk.hms.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/addUser")
    public Result addUser(@RequestBody CreateUserDto createUserDto){
        return userService.addUser(createUserDto);
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam int userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody GetUserDto getUserDto){
        return userService.updateUser(getUserDto);
    }

    @GetMapping("/getUserById")
    public DataResult<User> getUserById(@RequestParam int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/getUserByUsername")
    public DataResult<User> getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUsers")
    public DataResult<List<User>> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/addModerator")
    public Result addModerator(@RequestParam int userId){
        return userService.addModerator(userId);
    }

    @PostMapping("/removeModerator")
    public Result removeModerator(@RequestParam int userId){
        return userService.removeModerator(userId);
    }

    @PostMapping("/addStaff")
    public Result addStaff(@RequestParam int userId){
        return userService.addStaff(userId);
    }

    @PostMapping("/removeStaff")
    public Result removeStaff(@RequestParam int userId){
        return userService.removeStaff(userId);
    }

}
