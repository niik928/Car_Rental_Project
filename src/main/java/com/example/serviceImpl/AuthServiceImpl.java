package com.example.serviceImpl;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.enums.UserStatus;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.security.JwtService;
import com.example.service.AuthService;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    @Override
    public UserDto register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() ->
                        new RuntimeException("ROLE_USER not found"));

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        user.getRole().add(role);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String accessToken = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        UserDto userDto =
                modelMapper.map(user, UserDto.class);

        return new LoginResponse(
                accessToken,
                refreshToken,
                86400,
                "Bearer",
                userDto
        );
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {

        String email =
                jwtService.extractEmail(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        String newAccessToken =
                jwtService.generateToken(user);

        UserDto userDto = modelMapper.map(user , UserDto.class);

        return new LoginResponse(
                newAccessToken,
                refreshToken,
                86400,
                "Bearer",
                userDto
        );
    }
        @Override
        public String logout() {

            SecurityContextHolder.clearContext();

            return "Logout Successful";
        }
    }
