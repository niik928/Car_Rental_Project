package com.example.serviceImpl;

import com.example.dto.CarDto;
import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public CarDto addCar(CarDto carDto) {
        Car car = modelMapper.map(carDto , Car.class);
        Car savedCar = carRepository.save(car);
        return modelMapper.map(savedCar , CarDto.class);
    }

    @Override
    public CarDto updateCar(Long carId, CarDto carDto) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car Not Found"));
        car.setBrandName(carDto.getBrandName());
        Car updatedCar = carRepository.save(car);
        return modelMapper.map(updatedCar, CarDto.class);
    }

    @Override
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car Not Fpund"));
        carRepository.delete(car);

    }

    @Override
    public CarDto getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car Not Found"));

        return modelMapper.map(car , CarDto.class);
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream().map(car -> modelMapper.map(car ,CarDto.class))
                .collect(Collectors.toList());

    }
}
