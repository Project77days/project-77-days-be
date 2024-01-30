package org.example.domain;

import org.example.domain.exceptions.DomainException;
import org.example.domain.task.Task;
import org.example.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

  @Test
  public void givenAValidParams_whenCallNewTask_thenInstantiateATask(){
    final var expectedName = "ler 10 paginas ";
    final var expectedStatusTask = false;
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks = Task.newTask(expectedName,expectedDescription, expectedValue);

    Assertions.assertNotNull(actualTasks);
    Assertions.assertNotNull(actualTasks.getId());
    Assertions.assertEquals(expectedName, actualTasks.getName());
    Assertions.assertEquals(expectedStatusTask, actualTasks.getStatusTask());
    Assertions.assertEquals(expectedValue, actualTasks.getValue());
    Assertions.assertNotNull(actualTasks.getCreatedTime());
    Assertions.assertNotNull(actualTasks.getUpdateTime());
    Assertions.assertNull(actualTasks.getDeleteTime());
  }

  @Test
  public void givenAnInvalidNameNull_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' should be null";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(null ,expectedDescription, expectedValue);

    final var actualException =
      Assertions.assertThrows(DomainException.class, () -> actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
}
