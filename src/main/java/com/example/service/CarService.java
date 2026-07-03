package com.example.service;

import com.example.dto.CarDto;

import java.util.List;

public interface CarService {

    //add car
    CarDto addCar(CarDto carDto);

    //update car
    CarDto updateCar(Long carId , CarDto carDto);

    //delete car
    void deleteCar(Long carId);

    //get By car Id
    CarDto getCarById(Long carId);

    //Get By ALl cars
    List<CarDto> getAllCars();
}
