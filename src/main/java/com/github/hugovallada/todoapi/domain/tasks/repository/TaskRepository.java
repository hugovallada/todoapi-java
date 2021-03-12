package com.github.hugovallada.todoapi.domain.tasks.repository;

import com.github.hugovallada.todoapi.domain.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    @Modifying
    @Query("Update Task t SET t.status = true WHERE t.id = ?1")
    void updateStatusById(Long id);

}
