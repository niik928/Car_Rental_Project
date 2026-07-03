package com.example.service;

import com.example.dto.VehicleDto;

import java.util.List;

public interface VehicleService {


    VehicleDto addVehicle(Long variantId , VehicleDto vehicleDto);

    VehicleDto updateVehicle(Long vehicleId,VehicleDto vehicleDto);

    VehicleDto getVehicleById(Long vehicleId);

    List<VehicleDto> getAllVehicles();

    void deleteVehicle(Long vehicleId);
}
