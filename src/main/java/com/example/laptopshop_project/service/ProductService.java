package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products hanleSave(Products products) {
        return this.productRepository.save(products);
    }
    public List<Products> getAllProducts() {
        return this.productRepository.findAll();
    }
}
