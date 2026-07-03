package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "variant")

public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String variantName;
    private String fuelType;
    private String transmission;
    private Integer seatingCapacity;
    private Double pricePerDay;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "variant" , cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();
}
