package com.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VehicleDto {

    private Long id;
    private String vehicleNo;
    private String color;
    private Integer manufacturingYear;
    private String status;
}
