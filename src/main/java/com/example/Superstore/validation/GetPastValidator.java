package com.example.Superstore.validation;

import java.util.Date;

import jakarta.validation.ConstraintValidator;

public class GetPastValidator implements ConstraintValidator<GetPast, Date> {
    public boolean isValid(Date value, jakarta.validation.ConstraintValidatorContext context) {

        long today = new Date().getTime();
        long thatDay = value.getTime();

        return thatDay <= today;
    };
}
