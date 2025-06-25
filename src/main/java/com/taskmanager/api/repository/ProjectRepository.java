package com.taskmanager.api.repository;

import com.taskmanager.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface pour gérer les entités Projets.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
