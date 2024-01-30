package org.example.domain.exceptions;

import org.example.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException {
   private final List<Error> errors;

  private DomainException(String message ,List<Error> errors) {
    super(message);
    this.errors = errors;
  }

  public static DomainException with(Error errors){
    return  new DomainException(errors.message(), List.of(errors));
  }

  public static DomainException with(List<Error> errors){
    return  new DomainException("", errors);
  }

  public List<Error> getErrors() {
    return errors;
  }
}
