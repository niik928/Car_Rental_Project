package com.example.serviceImpl;

import com.example.dto.VariantDto;
import com.example.entity.Car;
import com.example.entity.Variant;
import com.example.repository.CarRepository;
import com.example.repository.VariantRepository;
import com.example.service.VariantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VariantServiceImpl implements VariantService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final VariantRepository variantRepository;

    @Override
    public VariantDto addVariant(Long carId, VariantDto variantDto) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car Not Found"));

        Variant variant = modelMapper.map(variantDto, Variant.class);
        variant.setCar(car);
        Variant savedVariant = variantRepository.save(variant);
        return modelMapper.map(savedVariant ,VariantDto.class);

    }

    @Override
    public VariantDto updateVariant(Long variantId, VariantDto variantDto) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(()-> new RuntimeException("Car Not Found"));
        variant.setVariantName(variantDto.getVariantName());
        variant.setFuelType(variantDto.getFuelType());
        variant.setTransmission(variantDto.getTransmission());
        variant.setSeatingCapacity(variantDto.getSeatingCapacity());
        variant.setPricePerDay(variantDto.getPricePerDay());

        return modelMapper.map(variantRepository.save(variant) , VariantDto.class);
    }

    @Override
    public VariantDto getVariantById(Long variantId) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(()-> new RuntimeException("Variant Not Found"));
        return  modelMapper.map(variant , VariantDto.class);
    }

    @Override
    public List<VariantDto> getAllVariant() {
        return variantRepository.findAll()
                .stream().map(v -> modelMapper.map(v,VariantDto.class))
                .toList();
    }

    @Override
    public List<VariantDto> getVariantByCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car Not Found"));

        return car.getVariants()
                .stream()
                .map(v -> modelMapper.map(v, VariantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVariant(Long variantId) {
        Variant variant = variantRepository.findById(variantId)
                .orElseThrow(()-> new RuntimeException("Variant Not Found"));
        variantRepository.delete(variant);

    }
}
