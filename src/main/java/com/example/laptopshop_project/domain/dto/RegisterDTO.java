package com.example.laptopshop_project.domain.dto;


import com.example.laptopshop_project.service.validator.RegisterChecked;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterChecked
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
