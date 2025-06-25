package com.taskmanager.api.repository;

import com.taskmanager.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface pour gérer les entités Projets.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByCreator_Id(Long userId);
}
