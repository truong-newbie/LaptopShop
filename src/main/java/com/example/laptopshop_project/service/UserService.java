package com.example.laptopshop_project.service;




import com.example.laptopshop_project.domain.Roles;
import com.example.laptopshop_project.domain.Users;
import com.example.laptopshop_project.domain.dto.RegisterDTO;
import com.example.laptopshop_project.repository.RoleRepository;
import com.example.laptopshop_project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public Roles getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public Users RegisterDTOtoUser(RegisterDTO registerDTO){
        Users user=new Users();

        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

}
