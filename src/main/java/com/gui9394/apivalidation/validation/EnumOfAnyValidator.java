package com.gui9394.apivalidation.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumOfAnyValidator implements ConstraintValidator<EnumOfAny, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumOfAny annotation) {
        acceptedValues = Stream.of(annotation.anyOf()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptedValues.contains(value);
    }
}
