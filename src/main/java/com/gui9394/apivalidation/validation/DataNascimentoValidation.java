package com.gui9394.apivalidation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataNascimentoValidation implements ConstraintValidator<DataValidation, String> {

  @Override
  public void initialize(DataValidation contactNumber) {
  }

  @Override
  public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
    if (contactField != null) {
      return contactField.equals("05-03-1998");
    }

    return false;
  }

}