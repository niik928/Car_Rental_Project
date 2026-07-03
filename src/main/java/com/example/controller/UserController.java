package com.example.controller;

import com.example.dto.DrivingLicenseDto;
import com.example.dto.UserDto;
import com.example.dto.UsersStatusDto;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/")
//    public String home(){
//        return "User API Working Successfully";
//    }


    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }
 //create user --  admin side
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    //get All users -- admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    //get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    //get user id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable String userId){
        return ResponseEntity.ok(userService.updateUser(userDto ,userId));
    }

    // Update User Status
    @PutMapping("/status/{userId}")
    public UserDto updateUserStatus(
            @PathVariable String userId,
            @RequestBody UsersStatusDto usersStatusDto) {

        return userService.updateUserStatus(userId, usersStatusDto);
    }
    //add driving license existing user
    @PostMapping("/{userId}/driving-license")
    public UserDto addDrivingLicense(
            @PathVariable String userId,
            @RequestBody DrivingLicenseDto dto) {

        return userService.addDrivingLicense(userId, dto);
    }
    // Get User Driving License
    @GetMapping("/driving-license/{userId}")
    public UserDto getUserDrivingLicense(
            @PathVariable String userId) {

        return userService.getUserDrivingLicense(userId);
    }
}
