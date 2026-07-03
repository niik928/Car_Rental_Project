package com.example.controller;

import com.example.dto.CarDto;
import com.example.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    //Add Car
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CarDto addCar(@RequestBody CarDto carDto){
        return carService.addCar(carDto);
    }

    //Update Car
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{carId}")
    public CarDto updateCar(@PathVariable Long carId,@RequestBody CarDto carDto){
        return carService.updateCar(carId, carDto);
    }

    //Delete car
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{carId}")
    public String deleteCar(@PathVariable Long carId){
        carService.deleteCar(carId);
        return "Car Deleted Successfully";
    }

    //Get All Car
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<CarDto> getAllCars(){
        return  carService.getAllCars();
    }

    // Get Car By Id
    @GetMapping("/{carId}")
    public CarDto GetCarById(@PathVariable Long carId){
      return carService.getCarById(carId);
    }

}
