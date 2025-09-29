package com.example.laptopshop_project.repository;

import com.example.laptopshop_project.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users save(Users user);
    List<Users> findAll();
    Optional<Users> findById(long id);

    void deleteById(long id);

    boolean existsByEmail(String email);


}
