package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

}
