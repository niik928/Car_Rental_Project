package com.example.serviceImpl;

import com.example.dto.DrivingLicenseDto;
import com.example.dto.UserDto;
import com.example.dto.UsersStatusDto;
import com.example.entity.DrivingLicense;
import com.example.entity.User;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repository.DrivingLicenseRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DrivingLicenseRepository drivingLicenseRepository;


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if(userDto.getEmail() == null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is Required");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException(("Email already exists"));
        }
        User user = modelMapper.map(userDto, User.class);
        //role assign here to user -- for authorization
        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved , UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new
                        ResourceNotFoundException("user not found with given email id"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id"));
        userRepository.delete(user);
    }


    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->
                        new RuntimeException(""));
        return modelMapper.map(user , UserDto.class);
    }

    @Override
    @Transactional()
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map((element) -> modelMapper.map(element, UserDto.class)).toList();
    }

    @Override
    public UserDto updateUserStatus(String userId, UsersStatusDto usersStatusDto) {
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setStatus(usersStatusDto.getStatus());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto addDrivingLicense(String userId, DrivingLicenseDto dto) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        DrivingLicense license = new DrivingLicense();

        license.setLicenseNo(dto.getLicenseNo());
        license.setIssueDate(dto.getIssueDate());
        license.setExpiryDate(dto.getExpiryDate());
        license.setImage(dto.getImage());

        license.setUser(user);

        drivingLicenseRepository.save(license);

        user.setDrivingLicense(license);

        return modelMapper.map(user, UserDto.class);
    }


    @Override
    public UserDto getUserDrivingLicense(String userId) {

        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        return modelMapper.map(user,UserDto.class);
    }

}
