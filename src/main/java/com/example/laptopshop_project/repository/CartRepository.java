package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Cart;
import com.example.laptopshop_project.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(Users user);


}
