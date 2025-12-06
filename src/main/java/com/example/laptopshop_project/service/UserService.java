package com.example.laptopshop_project.service;


import com.example.laptopshop_project.domain.Roles;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.domain.dto.RegisterDTO;
import com.example.laptopshop_project.repository.OrderRepository;
import com.example.laptopshop_project.repository.ProductRepository;
import com.example.laptopshop_project.repository.RoleRepository;
import com.example.laptopshop_project.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       ProductRepository productRepository,
                       OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Users handlesave(Users user) {
        return this.userRepository.save(user);
    }

    public Page<Users> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public Users updateUser(long id, Users updatedUser) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setRole(updatedUser.getRole());

        return this.userRepository.save(existingUser);
    }

    public Users getUserById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Roles getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Users RegisterDTOtoUser(RegisterDTO registerDTO) {
        Users user = new Users();

        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public Users getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }

}
