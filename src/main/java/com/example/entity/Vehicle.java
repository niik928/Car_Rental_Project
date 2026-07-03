package com.example.entity;

import com.example.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "vehicle_number")
    private String vehicleNo;
    private String color;
    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @OneToMany(mappedBy = "vehicle" , cascade = CascadeType.ALL)
    private List<Booking> bookings;




}
