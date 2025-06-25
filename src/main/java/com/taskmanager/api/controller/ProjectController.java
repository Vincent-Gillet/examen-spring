package com.taskmanager.api.controller;

import com.taskmanager.api.model.Project;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Contrôleur REST pour la gestion des projets.
 * Expose les endpoints pour les opérations CRUD sur les projets.
 */
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.saveProject(project);
        return new ResponseEntity<>(createdProject,HttpStatus.CREATED);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<Set<?>> getAllTasksOfProject(@PathVariable Long id) {
        Set<Task> tasks = projectService.getProjectById(id)
                .map(Project::getTask)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(tasks);

    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Project> getAllTasksOfProject(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
