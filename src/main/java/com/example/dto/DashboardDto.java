package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDto {
    private long totalUsers;
    private long totalCars;
    private long totalVehicles;
    private long totalBookings;
    private double totalRevenue;
}
