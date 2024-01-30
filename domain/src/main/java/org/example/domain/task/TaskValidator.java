package org.example.domain.task;

import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;
import org.example.domain.validation.Validator;

public class TaskValidator extends Validator {
  private final Task task;

  public TaskValidator(final Task aTask, ValidationHandler handler) {
    super(handler);
    this.task = aTask;
  }

  @Override
  public void validate() {
    if(this.task.getName() == null){
      this.validationHandler().append(new Error("'name' should be null"));
    }
  }
}
