package com.gui9394.apivalidation.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfEnum1Validator implements ConstraintValidator<ValueOfEnum1, CharSequence> {
    private List<String> acceptedValues;
 
    @Override
    public void initialize(ValueOfEnum1 annotation) {
        // Stream.of(annotation.groups()).map((Class<?> clazz) -> {
        //     System.out.println(clazz);

        //     return clazz;
        // });

        // acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
        //         .map(Enum::name)
        //         .collect(Collectors.toList());
    }
 
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
 
        return acceptedValues.contains(value.toString());
    }
}