package com.springframework.web.validation;


import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    private int min;

    public void initialize(ValidEmail constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email.length() < min) {
            return false;
        }

        if(!EmailValidator.getInstance().isValid(email)) {
            return false;
        }
            return true;
    }
}
