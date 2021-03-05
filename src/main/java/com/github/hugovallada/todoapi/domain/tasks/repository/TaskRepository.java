package com.github.hugovallada.todoapi.domain.tasks.repository;

import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
