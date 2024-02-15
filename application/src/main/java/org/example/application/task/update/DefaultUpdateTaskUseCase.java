package org.example.application.task.update;

import io.vavr.API;
import io.vavr.control.Either;
import org.example.domain.exceptions.DomainException;
import org.example.domain.task.Task;
import org.example.domain.task.TaskGateway;
import org.example.domain.task.TaskID;
import org.example.domain.validation.Error;
import org.example.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.control.Either.left;

public class DefaultUpdateTaskUseCase extends UpdateTaskUseCase {
  private final TaskGateway taskGateway;

  public DefaultUpdateTaskUseCase(TaskGateway taskGateway) {
    this.taskGateway = Objects.requireNonNull(taskGateway);
  }

  @Override
  public Either<Notification, UpdateTaskOutput> execute(UpdateTaskCommand inTask) {
    var id = TaskID.from(inTask.id());
    var name = inTask.name();
    var description = inTask.description();
    var value = inTask.value();

    var aTask = this.taskGateway.findById(id)
      .orElseThrow(notFound(id));

    var notification = Notification.create();

    aTask.update(name,description,value)
        .validate(notification);

    return notification.hasErrors() ? left(notification) : update(aTask);
  }

  private static Supplier<DomainException> notFound(TaskID id) {
    return () -> DomainException.with(
      new Error("Task with ID %s not found".formatted(id.getValue()))
    );
  }

  private Either<Notification, UpdateTaskOutput> update(Task task) {
    return API.Try(() -> this.taskGateway.update(task))
      .toEither()
      .bimap(Notification::create, UpdateTaskOutput::from);
  }
}
