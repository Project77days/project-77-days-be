package org.example.application.task.create;

public record CreateTaskCommand(
  String name,
  String description,
  String value
) {

  public static CreateTaskCommand with(
    String name,
    String description,
    String value
  ){
    return new CreateTaskCommand(name, description,value);
  }
}
