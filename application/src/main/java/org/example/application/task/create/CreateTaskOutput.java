package org.example.application.task.create;

import org.example.domain.task.Task;
import org.example.domain.task.TaskID;

public record CreateTaskOutput(
  TaskID id
) {
  public static CreateTaskOutput from(Task task){
    return new CreateTaskOutput(task.getId());
  }
}
