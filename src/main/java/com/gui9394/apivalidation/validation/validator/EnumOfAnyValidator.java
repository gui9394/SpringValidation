package com.gui9394.apivalidation.validation.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gui9394.apivalidation.validation.EnumOfAny;

@SuppressWarnings("all")
public class EnumOfAnyValidator implements ConstraintValidator<EnumOfAny, Enum> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumOfAny annotation) {
        acceptedValues = Stream.of(annotation.anyOf()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        return acceptedValues.contains(value.name());
    }
}
