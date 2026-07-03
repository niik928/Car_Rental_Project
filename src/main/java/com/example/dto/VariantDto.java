package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class VariantDto {
    private Long id;
    private String variantName;
    private String fuelType;
    private String transmission;
    private Integer seatingCapacity;
    private Double pricePerDay;
}
