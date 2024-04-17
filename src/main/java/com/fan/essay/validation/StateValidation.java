package com.fan.essay.validation;

import com.fan.essay.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {

    /**
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return false if the value does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if ("已发布".equals(value) || "草稿".equals(value)) {
            return true;
        }

        return false;
    }
}
