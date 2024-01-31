package org.example.domain.task;

import org.example.domain.validation.Error;
import org.example.domain.validation.ValidationHandler;
import org.example.domain.validation.Validator;

public class TaskValidator extends Validator {
  public static final int NAME_LENGTH_MAX = 255;
  public static final int NAME_LENGTH_MIN = 3;
  private final Task task;

  public TaskValidator(final Task aTask, ValidationHandler handler) {
    super(handler);
    this.task = aTask;
  }

  @Override
  public void validate() {
    checkNameConstrains();
  }

  private void checkNameConstrains() {
    final var taskName = this.task.getName();

    if (taskName == null) {
      this.validationHandler().append(new Error("'name' should be null"));
      return;
    }

    if (taskName.isBlank()) {
      this.validationHandler().append(new Error("'name' should be empty"));
      return;
    }

    if (taskName.length() > NAME_LENGTH_MAX || taskName.trim().length() < NAME_LENGTH_MIN) {
      this.validationHandler().append(new Error( "'name' must be between 3 and 255 character"));
      return;
    }
  }
}
