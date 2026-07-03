package com.example.controller;

import com.example.dto.*;
import com.example.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(
            @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                authService.refreshToken(
                        request.refreshToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logout Successful");
    }
}