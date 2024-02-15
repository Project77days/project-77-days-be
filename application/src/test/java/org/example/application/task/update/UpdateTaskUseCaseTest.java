package org.example.application.task.update;

import org.example.domain.task.Task;
import org.example.domain.task.TaskGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskUseCaseTest {
  @InjectMocks
  private DefaultUpdateTaskUseCase useCase;
  @Mock
  private TaskGateway taskGateway;

  @Test
  public void givenAValidCommand_WhenCallsUpdateTAsks_shouldReturnTaskId(){
    var aTask = Task.newTask("nameTask", "expectedDescription", "50");

    var expectedName = "read 10 pages";
    var expectedDescription = "read 10 pages";
    var expectedValue = "10";

    var expectedId = aTask.getId();

    var taskUpdate = UpdateTaskCommand.with(
      aTask.getId().getValue(),
      expectedName,
      expectedDescription,
      expectedValue
    );

    when(taskGateway.findById(eq(expectedId)))
      .thenReturn(Optional.of(aTask.clone()));

    when(taskGateway.update(any())).thenAnswer(returnsFirstArg());

    var actualOutput = useCase.execute(taskUpdate).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(taskGateway, times(1)).findById(expectedId);
    Mockito.verify(taskGateway, times(1)).update(
      argThat(aUpdateTask ->
          Objects.equals(expectedName, aUpdateTask.getName())
            && Objects.equals(expectedDescription, aUpdateTask.getDescription())
            && Objects.equals(expectedValue, aUpdateTask.getValue())
            && Objects.equals(expectedId, aUpdateTask.getId())
            && Objects.equals(aTask.getCreatedTime(), aUpdateTask.getCreatedTime())
            && aTask.getUpdateTime().isBefore(aUpdateTask.getUpdateTime())
        )
    );
  }

  @Test
  public void givenAInvalidNAme_WhenCallsCreateTAsks_shouldReturnDomainException(){
  }

  @Test
  public void givenAValidNAme_WhenGatewayThrowsRandomException_shouldReturnException(){
  }
}
