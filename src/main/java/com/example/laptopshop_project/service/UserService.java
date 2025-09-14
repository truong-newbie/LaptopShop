package com.example.laptopshop_project.service;




import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users handlesave(Users user) {
        return this.userRepository.save( user);
    }

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Users updateUser(long id,Users updatedUser) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());

        return this.userRepository.save(existingUser);
    }

    public Users getUserById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

}
