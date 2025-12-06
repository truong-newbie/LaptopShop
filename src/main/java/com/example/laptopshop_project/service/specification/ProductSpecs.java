package com.example.laptopshop_project.service.specification;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Products_;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Products> nameLike(String name) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Products_.NAME), "%" + name + "%");
    }
}
