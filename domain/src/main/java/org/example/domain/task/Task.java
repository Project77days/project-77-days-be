package org.example.domain.task;

import java.time.Instant;
import java.util.UUID;


public class Task {
  private String id;
  private String name;
  private String description;
  private Boolean statusTask;
  private String value;
  private Instant createdTime;
  private Instant updateTime;
  private Instant deleteTime;
  //private Type type;

  public Task(String id, String name, String description, Boolean statusTask, String value, Instant createdTime, Instant updateTime, Instant deleteTime) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.statusTask = false;
    this.value = value;
    this.createdTime = createdTime;
    this.updateTime = updateTime;
    this.deleteTime = deleteTime;
  }

  public static Task newTask(String name, String description, String value){
    final var id = UUID.randomUUID().toString();
    final var now = Instant.now();
    return  new Task(id, name, description, false, value, now, now,null);
  }

  public String getId() {
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