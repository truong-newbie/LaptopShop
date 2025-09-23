package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
    Products save(Products products);
    List<Products> findAll();

}
