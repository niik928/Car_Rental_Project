package com.example.repository;

import com.example.entity.Car;
import com.example.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant , Long> {

}
