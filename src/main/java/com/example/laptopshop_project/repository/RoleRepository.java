package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles , Long> {
    Roles findByName(String name);
}
