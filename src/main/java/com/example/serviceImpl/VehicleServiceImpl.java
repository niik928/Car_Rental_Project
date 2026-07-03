package com.example.serviceImpl;

import com.example.dto.VehicleDto;
import com.example.entity.Variant;
import com.example.entity.Vehicle;
import com.example.enums.VehicleStatus;
import com.example.repository.VariantRepository;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleService;
import com.sun.jdi.InvalidLineNumberException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VariantRepository variantRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleDto addVehicle(Long variantId, VehicleDto vehicleDto) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant Not Found"));

        Vehicle vehicle = modelMapper.map(vehicleDto ,Vehicle.class);
        vehicle.setVariant(variant);
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle ,VehicleDto.class);
    }

    @Override
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicel Not Found"));
        vehicle.setVehicleNo(vehicleDto.getVehicleNo());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setManufacturingYear(vehicleDto.getManufacturingYear());
        vehicle.setStatus(VehicleStatus.valueOf(vehicleDto.getStatus()));

        return modelMapper.map(vehicleRepository.save(vehicle),VehicleDto.class);
    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new RuntimeException("Vehicle Not Found"));

        return modelMapper.map(vehicle ,VehicleDto.class);
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream().map(vehicle1 -> modelMapper.map(vehicle1, VehicleDto.class))
                .toList();

    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new RuntimeException("Vehicle Not Found"));
        vehicleRepository.delete(vehicle);

    }
}
