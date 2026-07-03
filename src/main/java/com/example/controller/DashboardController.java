package com.example.controller;

import com.example.entity.DashboardDto;
import com.example.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String dashboard() {
        return "Welcome Dashboard";
    }
}
