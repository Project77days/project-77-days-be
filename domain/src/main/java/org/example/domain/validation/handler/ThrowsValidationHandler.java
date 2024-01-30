package org.example.domain.validation.handler;

import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
  @Override
  public ValidationHandler append(Error error) {
    throw DomainException.with(error);
  }

  @Override
  public ValidationHandler append(ValidationHandler handler) {
    throw DomainException.with(handler.getErrors());
  }

  @Override
  public ValidationHandler validate(Validation validation) {
   try {
     validation.validate();
   }catch (Exception ex){
     throw DomainException.with(new Error(ex.getMessage()));
   }
   return this;
  }

  @Override
  public List<Error> getErrors() {
    return null;
  }
}
