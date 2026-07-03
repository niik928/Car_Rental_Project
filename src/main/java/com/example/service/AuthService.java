package com.example.service;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(String refreshToken);

    String logout();
}
