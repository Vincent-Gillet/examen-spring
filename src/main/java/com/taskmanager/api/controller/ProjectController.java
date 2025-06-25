package com.taskmanager.api.controller;

import com.taskmanager.api.dto.ProjectCreateDTO;
import com.taskmanager.api.dto.ProjectGetTaskDTO;
import com.taskmanager.api.dto.TaskDTO;
import com.taskmanager.api.model.Project;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.User;
import com.taskmanager.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour la gestion des projets.
 * Expose les endpoints pour les opérations CRUD sur les projets.
 */
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


/*    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.saveProject(project);
        return new ResponseEntity<>(createdProject,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<ProjectCreateDTO> createProject(@RequestBody Project project) {
        Project createdProject = projectService.saveProject(project);
        ProjectCreateDTO dto = projectService.toDTOCreate(createdProject);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectGetTaskDTO> getAllTasksOfProject(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> {
                    project.getTask().size();
                    Set<TaskDTO> taskDTOs = project.getTask().stream()
                            .map(task -> new TaskDTO(task.getId(), task.getTitle()))
                            .collect(Collectors.toSet());
                    ProjectGetTaskDTO dto = new ProjectGetTaskDTO(project.getName(), taskDTOs);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Set<?>> getAllTasksAndDetailsOfProject(@PathVariable Long id) {
        Set<Task> tasks = projectService.getProjectById(id)
                .map(Project::getTask)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        return ResponseEntity.ok(tasks);
    }


}
