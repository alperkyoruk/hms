package com.alperkyoruk.hms.business.concretes;

import com.alperkyoruk.hms.business.abstracts.UserService;
import com.alperkyoruk.hms.business.constants.UserMessages;
import com.alperkyoruk.hms.core.result.*;
import com.alperkyoruk.hms.dataAccess.UserDao;
import com.alperkyoruk.hms.entities.DTOs.User.CreateUserDto;
import com.alperkyoruk.hms.entities.DTOs.User.GetUserDto;
import com.alperkyoruk.hms.entities.Role;
import com.alperkyoruk.hms.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Result addUser(CreateUserDto requestUserDto) {
        User user;
        if (requestUserDto.getBadgeNumber() != null && requestUserDto.getStaffPhoneNumber() != null) {
            // Register as staff
            user = User.builder()
                    .username(requestUserDto.getBadgeNumber())
                    .password(passwordEncoder.encode(requestUserDto.getStaffPhoneNumber()))
                    .email(requestUserDto.getEmail())
                    .authorities(Set.of(Role.ROLE_STAFF))
                    .build();
        } else if (requestUserDto.getEmail() != null && requestUserDto.getGuestPhoneNumber() != null) {
            // Register as guest
            user = User.builder()
                    .username(requestUserDto.getEmail())
                    .password(passwordEncoder.encode(requestUserDto.getGuestPhoneNumber()))
                    .email(requestUserDto.getEmail())
                    .authorities(Set.of(Role.ROLE_GUEST))
                    .build();
        } else {
            return new ErrorResult(UserMessages.UserCouldNotBeAdded);
        }

        userDao.save(user);
        return new SuccessResult(UserMessages.UserAddedSuccessfully);
    }

    @Override
    public Result deleteUser(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userDao.delete(userResponse);
        return new SuccessResult(UserMessages.UserDeletedSuccessfully);
    }

    @Override
    public Result updateUser(GetUserDto requestUserDto) {
        var userResponse = userDao.findById(requestUserDto.getId());
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userResponse.setUsername(requestUserDto.getUsername() == null ? userResponse.getUsername() : requestUserDto.getUsername());
        userResponse.setEmail(requestUserDto.getEmail() == null ? userResponse.getEmail() : requestUserDto.getEmail());
        userResponse.setAuthorities(requestUserDto.getAuthorities() == null ? userResponse.getAuthorities() : requestUserDto.getAuthorities());
        return new SuccessResult(UserMessages.UserUpdatedSuccessfully);
    }

    @Override
    public DataResult<User> getUserById(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorDataResult<>(UserMessages.UserNotFound);
        }


        return new SuccessDataResult<>(userResponse, UserMessages.UserSuccessfullyBrought);
    }

    @Override
    public DataResult<User> getUserByUsername(String username) {
        var userResponse = userDao.findByUsername(username);
        if(userResponse == null){
            return new ErrorDataResult<>(UserMessages.UserNotFound);
        }

        return new SuccessDataResult<>(userResponse, UserMessages.UserSuccessfullyBrought);
    }

    @Override
    public DataResult<List<User>> getUsers() {
        var userResponse = userDao.findAll();
        if(userResponse.isEmpty()){
            return new ErrorDataResult<>(UserMessages.UserNotFound);
        }

        return new SuccessDataResult<>(userResponse, UserMessages.UsersSuccessfullyBrought);
    }

    @Override
    public Result addModerator(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userResponse.addRole(Role.ROLE_MODERATOR);
        userDao.save(userResponse);
        return new SuccessResult(UserMessages.ModeratorAddedSuccessfully);
    }

    @Override
    public Result removeModerator(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userResponse.getAuthorities().remove(Role.ROLE_MODERATOR);
        return new SuccessResult(UserMessages.ModeratorRemovedSuccessfully);
    }

    @Override
    public Result addStaff(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userResponse.addRole(Role.ROLE_STAFF);
        userDao.save(userResponse);
        return new SuccessResult(UserMessages.StaffAddedSuccessfully);
    }

    @Override
    public Result removeStaff(int userId) {
        var userResponse = userDao.findById(userId);
        if(userResponse == null){
            return new ErrorResult(UserMessages.UserNotFound);
        }

        userResponse.getAuthorities().remove(Role.ROLE_STAFF);
        return new SuccessResult(UserMessages.StaffAddedSuccessfully);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getUserByUsername(username).getData();
        return user;
    }
}
