package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "driving_license")
@NoArgsConstructor
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String licenseNo;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String image;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
