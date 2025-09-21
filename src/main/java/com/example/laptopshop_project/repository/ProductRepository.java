package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
    public Products save(Products products);
    public List<Products> findAll();
}
