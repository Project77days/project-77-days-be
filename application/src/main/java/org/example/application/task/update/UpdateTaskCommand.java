package org.example.application.task.update;


public record UpdateTaskCommand(
  String id,
  String name,
  String description,
  String value
) {
  public static UpdateTaskCommand with(
    String id,
    String name,
    String description,
    String value
  ){
    return new UpdateTaskCommand(id, name, description,value);
  }
}
