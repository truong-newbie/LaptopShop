package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Orders;
import com.example.laptopshop_project.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAll();

    List<Orders> findByUser(Users user);

}
