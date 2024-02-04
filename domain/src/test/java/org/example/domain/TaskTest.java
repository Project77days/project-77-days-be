package org.example.domain;

import org.example.domain.exceptions.DomainException;
import org.example.domain.task.Task;
import org.example.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class TaskTest {

  @Test
  @DisplayName("givenAValidParams_whenCallNewTask_thenInstantiateATask")
  public void givenAValidParams_whenCallNewTask_thenInstantiateATask(){
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks = Task.newTask(expectedName,expectedDescription, expectedValue);

    Assertions.assertNotNull(actualTasks);
    Assertions.assertNotNull(actualTasks.getId());
    Assertions.assertEquals(expectedName, actualTasks.getName());
    Assertions.assertEquals(expectedValue, actualTasks.getValue());
    Assertions.assertNotNull(actualTasks.getCreatedTime());
    Assertions.assertNotNull(actualTasks.getUpdateTime());
    Assertions.assertNull(actualTasks.getDeleteTime());
  }

  @Test
  @DisplayName("givenAnInvalidNameNull_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAnInvalidNameNull_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(null ,expectedDescription, expectedValue);

    final var actualException =
      Assertions.assertThrows(DomainException.class, () -> actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  @DisplayName("givenAnInvalidEmptyNull_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAnInvalidEmptyNull_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(" " ,expectedDescription, expectedValue);

    final var actualException =
      Assertions.assertThrows(DomainException.class, () -> actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  @DisplayName("givenAnInvalidNameLengthLessThan3_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAnInvalidNameLengthLessThan3_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedName = "le ";
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' must be between 3 and 255 character";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(expectedName ,expectedDescription, expectedValue);

    final var actualException =
      Assertions.assertThrows(DomainException.class, () -> actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  @DisplayName("givenAnInvalidNameLengthMoreThan255_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAnInvalidNameLengthMoreThan255_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedName = """
        Com este commit, a otimização de performance da renderização do DOM corrigiu o bug de compilação multi-plataforma de forma asincrona.
        Nesse pull request, a compilação final do programa superou o desempenho de estados estáticos nos componentes da UI.
        Explica pro Product Onwer que o gerenciador de dependências do frontend complexificou o merge na compilação de templates literais.
      """;
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' must be between 3 and 255 character";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(expectedName ,expectedDescription, expectedValue);

    final var actualException =
      Assertions.assertThrows(DomainException.class, () -> actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  @DisplayName("givenAInvalidEmptyDescription_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAInvalidEmptyDescription_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "  ";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(expectedName ,expectedDescription, expectedValue);

    Assertions.assertDoesNotThrow(() ->actualTasks.validate(new ThrowsValidationHandler()));
  }

  @Test
  @DisplayName("givenAValidStatusTasksTrue_whenCallNewTaskAndValidate_thenNotInstantiateATask")
  public void givenAValidStatusTasksTrue_whenCallNewTaskAndValidate_thenNotInstantiateATask(){
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var actualTasks =
      Task.newTask(expectedName ,expectedDescription, expectedValue);

    Assertions.assertDoesNotThrow(() ->actualTasks.validate(new ThrowsValidationHandler()));

    Assertions.assertNotEquals(actualTasks.getStatusTask(), true);
  }

  @Test
  @DisplayName("givenAValidStatusTasksTrue_whenCallNewTaskAndValidate_thenUpdateDate")
  public void givenAValidStatusTasksTrue_whenCallNewTaskAndValidate_thenUpdateDate() throws InterruptedException {
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "ler no minimo 10 paginas por dia";
    final var expectedValue = "10";

    final var  task =
      Task.newTask(expectedName ,expectedDescription, expectedValue);
    final var updatedAt = task.getUpdateTime();

    Assertions.assertFalse(task.getStatusTask());
    Assertions.assertDoesNotThrow(() -> task.validate(new ThrowsValidationHandler()));

    Thread.sleep(250);
    final var actualTasks = task.done();
    final var actualUpdateAt = actualTasks.getUpdateTime();

    Assertions.assertEquals(actualTasks.getStatusTask(), true);
    Assertions.assertTrue(updatedAt.isBefore(actualUpdateAt));
    Assertions.assertEquals(expectedName, actualTasks.getName());
    Assertions.assertEquals(expectedValue, actualTasks.getValue());
    Assertions.assertNotNull(actualTasks.getCreatedTime());
    Assertions.assertNull(actualTasks.getDeleteTime());
  }


  @Test
  @DisplayName("givenAValidStatusTasksFalse_whenCallNewTaskAndValidate_thenUpdateDate")
  public void givenAValidStatusTasksFalse_whenCallNewTaskAndValidate_thenUpdateDate(){
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "ler no minimo 10 paginas por dia";
    final var expectedValue = "10";

    final var  task =
      Task.newTask(expectedName ,expectedDescription, expectedValue);
    final var updatedTask = task.done();
    final var updatedAt = updatedTask.getUpdateTime();

    Assertions.assertTrue(task.getStatusTask());
    Assertions.assertDoesNotThrow(() ->task.validate(new ThrowsValidationHandler()));

    final var actualTasks = task.notDone();
    final var actualUpdateAt = actualTasks.getUpdateTime();

    Assertions.assertFalse(task.getStatusTask());
    Assertions.assertEquals(actualTasks.getStatusTask(), false);
    Assertions.assertTrue(updatedAt.isBefore(actualUpdateAt));
    Assertions.assertEquals(expectedName, actualTasks.getName());
    Assertions.assertEquals(expectedValue, actualTasks.getValue());
    Assertions.assertNotNull(actualTasks.getCreatedTime());
    Assertions.assertNull(actualTasks.getDeleteTime());
  }

  @Test
  @DisplayName("givenAValidStatusTasksDelete_whenCallNewTaskAndValidate_thenDeleteDateNotNull")
  public void givenAValidStatusTasksDelete_whenCallNewTaskAndValidate_thenUpdateDate() throws InterruptedException {
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "ler no minimo 10 paginas por dia";
    final var expectedValue = "10";

    final var  task =
      Task.newTask(expectedName ,expectedDescription, expectedValue);
    final var updatedAt = task.getUpdateTime();

    Assertions.assertNull(task.getDeleteTime());
    Assertions.assertDoesNotThrow(() ->task.validate(new ThrowsValidationHandler()));

    Thread.sleep(250);
    final var actualTask = task.delete();
    final var actualUpdateAt = actualTask.getUpdateTime();

    Assertions.assertTrue(updatedAt.isBefore(actualUpdateAt));
    Assertions.assertNotNull(task.getCreatedTime());
    Assertions.assertNotNull(task.getUpdateTime());
    Assertions.assertNotNull(task.getDeleteTime());
  }


  @Test
  @DisplayName("givenAValidTask_whenCallUpdate_thenReturnTaskUpdated")
  public void givenAValidTask_whenCallUpdate_thenReturnTaskUpdated() throws InterruptedException {
    final var expectedName = "ler 10 paginas ";
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var aTask = Task.newTask("Tasks", " task", "check");

    Assertions.assertDoesNotThrow(() -> aTask.validate(new ThrowsValidationHandler()));

    Thread.sleep(250);
    final var updateAt = aTask.getUpdateTime();
    final var createAt = aTask.getCreatedTime();

    final var actualTask = aTask.update(expectedName,expectedDescription, expectedValue);

    Assertions.assertDoesNotThrow(() -> actualTask.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(aTask.getId(), actualTask.getId());
    Assertions.assertEquals(createAt, actualTask.getCreatedTime());
    Assertions.assertTrue(updateAt.isBefore(actualTask.getUpdateTime()));
    Assertions.assertNull(actualTask.getDeleteTime());

    Assertions.assertEquals(expectedName, actualTask.getName());
    Assertions.assertEquals(expectedValue, actualTask.getValue());
    Assertions.assertEquals(expectedDescription, actualTask.getDescription());
  }

  @Test
  @DisplayName("givenAValidTask_whenCallUpdateWithInvalidParams_thenReturnTaskUpdated")
  public void givenAValidTask_whenCallUpdateWithInvalidParams_thenReturnTaskUpdated(){
    final String expectedName = null;
    final var expectedDescription = "false";
    final var expectedValue = "10";

    final var aTask = Task.newTask("Tasks", " task", "check");

    Assertions.assertDoesNotThrow(() -> aTask.validate(new ThrowsValidationHandler()));

    final var updateAt = aTask.getUpdateTime();
    final var createAt = aTask.getCreatedTime();

    final var actualTask = aTask.update(expectedName,expectedDescription, expectedValue);

    Assertions.assertEquals(aTask.getId(), actualTask.getId());
    Assertions.assertEquals(createAt, actualTask.getCreatedTime());
    Assertions.assertTrue(updateAt.isBefore(actualTask.getUpdateTime()));
    Assertions.assertNull(actualTask.getDeleteTime());

    Assertions.assertEquals(expectedName, actualTask.getName());
    Assertions.assertEquals(expectedValue, actualTask.getValue());
    Assertions.assertEquals(expectedDescription, actualTask.getDescription());
  }
}
