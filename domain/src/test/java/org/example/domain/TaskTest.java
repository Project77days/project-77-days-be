package org.example.domain;

import org.example.domain.task.Task;
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
  }
}
