package com.example.springform.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RegexIdValidator implements ConstraintValidator<RegexIdentificator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.matches("[0-9]")) {
            return true;
        } else {
            return false;
        }
    }
}
