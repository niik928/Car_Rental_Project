package com.example.repository;

import com.example.entity.DrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense , Long> {
}
