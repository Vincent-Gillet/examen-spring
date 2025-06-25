package com.taskmanager.api.repository;

import com.taskmanager.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface pour gérer les entités Task.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
