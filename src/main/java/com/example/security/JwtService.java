package com.example.security;

import com.example.entity.Role;
import com.example.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;

    public JwtService(
            @Value("${security.jwt.secret}") String secret) {

        this.key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("roles",
                        user.getRole()
                                .stream()
                                .map(Role::getName)
                                .toList())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 15 * 60 * 1000
                        ))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 7L * 24 * 60 * 60 * 1000
                        ))
                .claim("type", "refresh")
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}