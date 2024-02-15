package org.example.application.task.update;

import org.example.domain.task.Task;
import org.example.domain.task.TaskID;

public record UpdateTaskOutput(
  TaskID id
) {
  public static UpdateTaskOutput from(Task task){
    return new UpdateTaskOutput(task.getId());
  }
}
