package com.example.service;

import com.example.dto.VariantDto;

import java.util.List;

public interface VariantService {

    //create/add variant
    VariantDto addVariant(Long carId, VariantDto variantDto);

    //update variant
    VariantDto updateVariant( Long variantId , VariantDto variantDto);

    //Get By Variant Id
    VariantDto getVariantById(Long variantId);

    //Get All Variant
    List<VariantDto> getAllVariant();

    //Get Variants By Car - specific car ke variants
    List<VariantDto> getVariantByCar(Long carId);

    //delete Variant
    void deleteVariant(Long variantId);


}
