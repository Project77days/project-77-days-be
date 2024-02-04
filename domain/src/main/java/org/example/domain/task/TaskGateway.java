package org.example.domain.task;

import org.example.domain.pagination.Pagination;

import java.util.Optional;

public interface TaskGateway {
  Task create(Task task);
  void deleteById(TaskID ID);
  Optional<Task> findById(TaskID ID);
  Task update(Task task);
  Pagination<Task> findAll(TaskSearchQuery query);
}
