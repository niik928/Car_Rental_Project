package com.example.dto;
public record LoginResponse(

        String accessToken,
        String refreshToken,
        long expiresIn,
        String tokenType,
        UserDto userDto
) {
}
