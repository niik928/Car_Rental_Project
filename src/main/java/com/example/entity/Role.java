package com.example.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name" ,unique = true,nullable = false)
    private String name;


    // Getters and Setters
}