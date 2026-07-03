package com.example.service;

import com.example.dto.DrivingLicenseDto;
import com.example.dto.UserDto;
import com.example.dto.UsersStatusDto;


public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    //get user by email
    UserDto getUserByEmail(String email);

    //update user
    UserDto updateUser(UserDto userDto , String userId);

    //delete user
    void deleteUser(String userId);

    //get user by id
    UserDto getUserById(String userId);

    //get all users
    Iterable<UserDto> getAllUsers();

    //update user status
    UserDto updateUserStatus(String userId , UsersStatusDto usersStatusDto);

    //get user driving license
    UserDto getUserDrivingLicense(String userId);

    //add driving license
    UserDto addDrivingLicense(String userId, DrivingLicenseDto dto);
}
