package org.example.application.task.create;

import io.vavr.API;
import io.vavr.control.Either;
import org.example.domain.task.Task;
import org.example.domain.task.TaskGateway;
import org.example.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateTaskUseCase extends CreateTaskUseCase{
  private final TaskGateway taskGateway;

  public DefaultCreateTaskUseCase(TaskGateway taskGateway) {
    this.taskGateway = Objects.requireNonNull(taskGateway);
  }

  @Override
  public Either<Notification, CreateTaskOutput> execute(CreateTaskCommand command) {
    var name = command.name();
    var description = command.description();
    var value = command.value();

    var notification = Notification.create();

    var task = Task.newTask(name, description, value);
    task.validate(notification);

    return notification.hasErrors() ? Either.left(notification) : create(task);
  }

  private  Either<Notification, CreateTaskOutput>  create(Task task) {
    //implemetação funcional com possibilidade de volta de dois tipos
    return API.Try(() -> this.taskGateway.create(task))
      .toEither()
      .map(CreateTaskOutput::from)
      .mapLeft(Notification::create);
  }
}
