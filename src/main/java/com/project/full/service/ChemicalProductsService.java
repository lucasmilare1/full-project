package com.project.full.service;

import com.project.full.domain.dto.ChemicalProductsDTO;
import com.project.full.domain.entity.ChemicalProducts;
import com.project.full.domain.repository.ChemicalProductRepository;
import com.project.full.exceptions.ChemicalProductException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ChemicalProductsService {
    @Autowired
    private ChemicalProductRepository repository;

    @Autowired
    private ModelMapper converter;

    public ChemicalProductsDTO saveNewChemicalProduct(ChemicalProductsDTO product) throws ChemicalProductException {
        try {
            ChemicalProducts chemicalProduct = new ChemicalProducts();
            chemicalProduct.setProductName(product.getProductName());
            chemicalProduct.setDanger(product.getDanger());
            chemicalProduct.setClassification(product.getClassification());
            return converter.map(repository.save(chemicalProduct), ChemicalProductsDTO.class);
        } catch (Exception e) {
            throw new ChemicalProductException("Fail to save new chemical product");
        }
    }




}
