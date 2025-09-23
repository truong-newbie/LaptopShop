package com.example.laptopshop_project.service;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Products> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Products updateProduct(long id, Products updateProduct) {
        Products existingProduct= this.productRepository.findById(id).get();
        if(existingProduct == null){
            throw new RuntimeException("Product not found");
        }
        existingProduct.setName(updateProduct.getName());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setDetailDesc(updateProduct.getDetailDesc());
        existingProduct.setShortDesc(updateProduct.getShortDesc());
        existingProduct.setQuantity(updateProduct.getQuantity());
        existingProduct.setFactory(updateProduct.getFactory());
        existingProduct.setTarget(updateProduct.getTarget());
        return this.productRepository.save(existingProduct);
    }
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }
}
