package org.example.domain.task;

import org.example.domain.AggregateRoot;

import java.time.Instant;


public class Task extends AggregateRoot<TaskID> {
  private String name;
  private String description;
  private Boolean statusTask;
  private String value;
  private Instant createdTime;
  private Instant updateTime;
  private Instant deleteTime;
  //private Type type;

  public Task(TaskID id, String name, String description, Boolean statusTask, String value, Instant createdTime, Instant updateTime, Instant deleteTime) {
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
    return  new Task(id, name, description, false, value, now, now,null);
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