package com.example.laptopshop_project.domain.dto;


import com.example.laptopshop_project.service.validator.RegisterChecked;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterChecked
public class RegisterDTO {
    @Size(min=3, message = "firstName phải có tối thiểu 3 kí tự")
    private String firstName;
    private String lastName;

    @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String password;
    @Size(min=3, message=" Password phải có tối thiểu 3 ký tự")
    private String confirmPassword;
}
