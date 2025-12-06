package com.example.laptopshop_project.service.specification;

import com.example.laptopshop_project.domain.Products;
import com.example.laptopshop_project.domain.Products_;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecs {
    public static Specification<Products> nameLike(String name) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Products_.NAME), "%" + name + "%");
    }

    //case1
    public static Specification<Products> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Products_.PRICE), price);
    }

    //case2
    public static Specification<Products> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(Products_.PRICE), price);
    }

    //case3
    public static Specification<Products> matchFactory(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Products_.FACTORY), factory);
    }

    //case4
    public static Specification<Products> matchListFactory(List<String> factory) {
        return (root, query, cb) -> root.get(Products_.FACTORY).in(factory);
    }

    //case5
    public static Specification<Products> matchPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.gt(root.get(Products_.PRICE), min),
                criteriaBuilder.le(root.get(Products_.PRICE), max));
    }

    //case6
    public static Specification<Products> matchMultiplePrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(Products_.PRICE), min, max);
    }


}
