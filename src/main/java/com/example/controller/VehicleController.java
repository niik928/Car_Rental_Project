package com.example.controller;

import com.example.dto.VehicleDto;
import com.example.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    //Add Vehicle
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{variantId}")
    public VehicleDto addVehicle(@PathVariable Long variantId, @RequestBody VehicleDto vehicleDto) {
        return vehicleService.addVehicle(variantId, vehicleDto);
    }

    //update vehicle
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{vehicleId}")
    public VehicleDto updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleDto vehicleDto) {
        return vehicleService.updateVehicle(vehicleId, vehicleDto);
    }

    //delete vehicle
    @DeleteMapping("/{vehicleId}")
    public String deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return "Vehicle deleted Successfully";
    }

    //Get All Vehicle
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<VehicleDto> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

    //Get By VehicleId
    @GetMapping("/{vehicleId}")
    public VehicleDto getByVehicleId(@PathVariable Long vehicleId){
        return vehicleService.getVehicleById(vehicleId);
    }

}
