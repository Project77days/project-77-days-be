package org.example.application.task.update;

import io.vavr.control.Either;
import org.example.application.UseCase;
import org.example.domain.validation.handler.Notification;

public abstract class UpdateTaskUseCase
  extends UseCase<UpdateTaskCommand, Either<Notification, UpdateTaskOutput>> {
}
