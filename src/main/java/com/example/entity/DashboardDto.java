package com.example.entity;

import lombok.Data;

@Data
public class DashboardDto {

    private Long totalUsers;
    private Long totalBookings;
    private Long totalCars;
    private Double totalRevenue;
}