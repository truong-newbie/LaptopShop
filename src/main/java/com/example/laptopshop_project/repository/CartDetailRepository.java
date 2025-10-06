package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Cart;
import com.example.laptopshop_project.domain.CartDetail;
import com.example.laptopshop_project.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProducts(Cart cart, Products products);

    CartDetail findByCartAndProducts(Cart cart, Products products);

    Long cart(Cart cart);

}
