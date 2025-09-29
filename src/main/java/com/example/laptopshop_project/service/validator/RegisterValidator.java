package com.example.laptopshop_project.service.validator;

import com.example.laptopshop_project.domain.dto.RegisterDTO;
import com.example.laptopshop_project.repository.UserRepository;
import com.example.laptopshop_project.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        if (user == null) {
            return true;
        }

        boolean valid = true;

        if (user.getPassword() == null || user.getConfirmPassword() == null) {
            return false;
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords phải trùng khớp")
                    .addPropertyNode("confirmPassword")
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
            valid = false;
        }

        if(this.userService.checkEmailExist(user.getEmail())){
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;

        }

        return valid;
    }
    //check email


}
