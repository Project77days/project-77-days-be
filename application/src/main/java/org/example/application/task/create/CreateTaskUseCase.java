package org.example.application.task.create;

import io.vavr.control.Either;
import org.example.application.UseCase;
import org.example.domain.validation.handler.Notification;

public abstract class CreateTaskUseCase
  extends UseCase< CreateTaskCommand, Either<Notification, CreateTaskOutput>> {

}
