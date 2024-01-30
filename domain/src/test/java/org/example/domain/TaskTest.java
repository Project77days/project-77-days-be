package org.example.domain;

import org.example.domain.exceptions.DomainException;
import org.example.domain.task.Task;
import org.example.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {

  @Test
  @DisplayName("givenAValidParams_whenCallNewTask_thenInstantiateATask")
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
  @DisplayName("givenAnInvalidNameNull_whenCallNewTaskAndValidate_thenShouldException")
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

  @Test
  @DisplayName("givenAnInvalidEmptyNull_whenCallNewTaskAndValidate_thenShouldException")
  public void givenAnInvalidEmptyNull_whenCallNewTaskAndValidate_thenShouldException(){
    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "'name' should be empty";
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

    Assertions.assertNotEquals(actualTasks.getStatusTask(), true);

  }
}
