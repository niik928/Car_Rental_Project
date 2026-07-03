package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DrivingLicenseDto {
    private String licenseNo;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String image;
}
