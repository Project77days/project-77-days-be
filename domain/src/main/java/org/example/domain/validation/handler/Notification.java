package org.example.domain.validation.handler;

import org.example.domain.exceptions.DomainException;
import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
  private List<Error> listErrors;

  public Notification(List<Error> listErrors) {
    this.listErrors = listErrors;
  }

  public static Notification create(){
    return  new Notification(new ArrayList<>());
  }

  public static Notification create(Error error){
    return  new Notification(new ArrayList<>()).append(error);
  }

  public static Notification create(Throwable throwable){
    return create(new Error(throwable.getMessage()));
  }

  @Override
  public Notification append(Error error) {
    this.listErrors.add(error);
    return this;
  }

  @Override
  public Notification append(ValidationHandler handler) {
    this.listErrors.addAll(handler.getErrors());
    return this;
  }

  @Override
  public Notification validate(Validation validation) {
    try{
      validation.validate();
    }catch(DomainException ex){
      this.listErrors.addAll(ex.getErrors());
    }catch (Throwable t){
      this.listErrors.add(new Error(t.getMessage()));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return this.listErrors;
  }
}
