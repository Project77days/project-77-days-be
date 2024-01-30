package org.example.domain.task;

import org.example.domain.AggregateRoot;
import org.example.domain.validation.ValidationHandler;

import java.time.Instant;

public class Task extends AggregateRoot<TaskID> {
  private final String name;
  private final String description;
  private final Boolean statusTask;
  private final String value;
  private final Instant createdTime;
  private final Instant updateTime;
  private final Instant deleteTime;
  //private Type type;

  public Task(TaskID id, String name, String description, String value, Instant createdTime, Instant updateTime, Instant deleteTime) {
    super(id);
    this.name = name;
    this.description = description;
    this.statusTask = false;
    this.value = value;
    this.createdTime = createdTime;
    this.updateTime = updateTime;
    this.deleteTime = deleteTime;
  }

  public static Task newTask(String name, String description, String value){
    final var id = TaskID.unique();
    final var now = Instant.now();
    return  new Task(id, name, description, value, now, now,null);
  }

  @Override
  public void validate(ValidationHandler handler) {
    new  TaskValidator(this, handler).validate();
  }

  public TaskID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getStatusTask() {
    return statusTask;
  }

  public String getValue() {
    return value;
  }

  public Instant getCreatedTime() {
    return createdTime;
  }

  public Instant getUpdateTime() {
    return updateTime;
  }

  public Instant getDeleteTime() {
    return deleteTime;
  }
}