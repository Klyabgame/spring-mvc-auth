package com.springbootmvc.crudspringbootmvc.validation;

import com.springbootmvc.crudspringbootmvc.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    private final UserService service;

    public ExistsByUsernameValidation(UserService service) {
        this.service = service;
    }

    @Override
    public void initialize(ExistsByUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !service.existsByUsername(username);
    }
}
