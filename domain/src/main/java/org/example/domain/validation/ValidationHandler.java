package org.example.domain.validation;

import java.util.List;

//interface fluent
public interface ValidationHandler {
  ValidationHandler append(Error error);

  ValidationHandler append(ValidationHandler handler);

  ValidationHandler validate(Validation validation);

  default Boolean hasErrors(){
    return  getErrors() != null && !getErrors().isEmpty();
  }

  default Error firstError(){
    if(getErrors() != null && !getErrors().isEmpty()){
      return  getErrors().get(0);
    }else {
      return null;
    }
  }

  List<Error> getErrors();

  public interface Validation{
    void validate();
  }
}
