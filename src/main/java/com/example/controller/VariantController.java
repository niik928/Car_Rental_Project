package com.example.controller;

import com.example.dto.VariantDto;
import com.example.service.VariantService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
@AllArgsConstructor
public class VariantController {

    private final VariantService variantService;

    //add variant to car
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{carId}")
    public VariantDto addVariant(@PathVariable Long carId, @RequestBody VariantDto variantDto){
        return variantService.addVariant(carId, variantDto);
    }

    //update variant to car
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{variantId}")
    public VariantDto updateVariant(@PathVariable Long variantId, @RequestBody VariantDto variantDto){
        return variantService.updateVariant(variantId ,variantDto);
    }

    //get variants by car
    @GetMapping("/car/{carId}")
    public List<VariantDto> getVariantsByCar(@PathVariable Long carId){
        return variantService.getVariantByCar(carId);
    }

    //delete variant
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{variantId}")
    public String deleteVariant(@PathVariable Long variantId){
        variantService.deleteVariant(variantId);
        return "Variant deleted successfully";
    }

    //Get Variant By Id
    @GetMapping("/{variantId}")
    public VariantDto getVariantById(@PathVariable Long variantId){
        return variantService.getVariantById(variantId);
    }

    //Get All Variants
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public  List<VariantDto> getAllVariants(){
        return variantService.getAllVariant();
    }
}
