package org.example.domain.task;

import org.example.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class TaskID extends Identifier {
  private final String value;

  private TaskID(final String value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  public static TaskID unique(){
    return TaskID.from(UUID.randomUUID());
  }

  public static  TaskID from(final String id){
    return new TaskID(id);
  }

  public static  TaskID from(final UUID id){
    return new TaskID(id.toString().toLowerCase());
  }

  public String getValue(){
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskID taskID = (TaskID) o;
    return Objects.equals(getValue(), taskID.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
