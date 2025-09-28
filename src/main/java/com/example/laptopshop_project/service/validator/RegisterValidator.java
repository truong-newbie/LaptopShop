package com.example.laptopshop_project.service.validator;

import com.example.laptopshop_project.domain.dto.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

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
            context.buildConstraintViolationWithTemplate("Passwords must match")
                    .addPropertyNode("confirmPassword")
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
