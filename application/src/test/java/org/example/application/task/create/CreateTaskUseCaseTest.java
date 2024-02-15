package org.example.application.task.create;

import org.example.domain.task.TaskGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {
  @InjectMocks
  private DefaultCreateTaskUseCase useCase;
  @Mock
  private TaskGateway taskGateway;

  @Test
  public void givenAValidCommand_WhenCallsCreateTasks_shouldReturnTaskId(){
    var expectName = "read 10 pages";
    var expectDescription = "read 10 pages";
    var expectedValue = "10";
    var command = CreateTaskCommand.with(expectName,expectDescription,expectedValue);

    when(taskGateway.create(any()))
      .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(command).get();

    Assertions.assertNotNull(actualOutput.id());
    verify(taskGateway, times(1)).create(argThat(aTasks ->
          Objects.equals(expectName, aTasks.getName())
          && Objects.equals(expectDescription, aTasks.getDescription())
          && Objects.equals(expectedValue, aTasks.getValue())
          && Objects.nonNull(aTasks.getId())
          && Objects.nonNull(aTasks.getCreatedTime())
          && Objects.nonNull(aTasks.getUpdateTime())
          && Objects.isNull(aTasks.getDeleteTime())
      ));
  }

  @Test
  public void givenAInvalidNAme_WhenCallsCreateTAsks_shouldReturnDomainException(){
    String expectName = null;
    var expectDescription = "read 10 pages";
    var expectedValue = "10";
    var expectedErrorMessage = "'name' should not be null" ;
    var expectedErrorCount = 1;

    var command = CreateTaskCommand
      .with(expectName,expectDescription,expectedValue);

    final var notification = useCase.execute(command).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    verify(taskGateway, times(0)).create(any());
  }

  @Test
  public void givenAValidNAme_WhenGatewayThrowsRandomException_shouldReturnException(){
    var expectName = "read 10 pages";
    var expectDescription = "read 10 pages";
    var expectedValue = "10";
    var expectedErrorMessage = "Gateway error" ;
    var expectedErrorCount = 1;

    var command = CreateTaskCommand.with(expectName,expectDescription,expectedValue);

    when(taskGateway.create(any()))
      .thenThrow(new IllegalStateException("Gateway error"));

    final var notification = useCase.execute(command).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());


    verify(taskGateway, times(1)).create(any());

    verify(taskGateway, times(1)).create(argThat(aTasks ->
      Objects.equals(expectName, aTasks.getName())
        && Objects.equals(expectDescription, aTasks.getDescription())
        && Objects.equals(expectedValue, aTasks.getValue())
        && Objects.nonNull(aTasks.getId())
        && Objects.nonNull(aTasks.getCreatedTime())
        && Objects.nonNull(aTasks.getUpdateTime())
        && Objects.isNull(aTasks.getDeleteTime())
    ));
  }
}
