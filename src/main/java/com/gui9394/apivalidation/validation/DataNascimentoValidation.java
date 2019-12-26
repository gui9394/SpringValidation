package com.gui9394.apivalidation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataNascimentoValidation implements ConstraintValidator<DateValidation, String> {

  @Override
  public void initialize(DateValidation dateValidation) {
  }

  @Override
  public boolean isValid(String contactField, ConstraintValidatorContext context) {
    if (contactField != null) {
      return contactField.equals("05-03-1998");
    }

    return false;
  }

}