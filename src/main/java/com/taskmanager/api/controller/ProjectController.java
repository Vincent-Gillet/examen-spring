package com.taskmanager.api.controller;

import com.taskmanager.api.dto.*;
import com.taskmanager.api.model.Project;
import com.taskmanager.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                    List<TaskDTO> taskDTOs = project.getTask().stream()
                            .map(task -> new TaskDTO(task.getId(), task.getTitle()))
                            .collect(Collectors.toList());
                    ProjectGetTaskDTO dto = new ProjectGetTaskDTO(project.getName(), taskDTOs);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/tasks")
    public ResponseEntity<ProjectGetTaskDetailsDTO> getAllTasksAndDetailsOfProject(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> {
                    project.getTask().size();
                    List<TaskGetDTO> taskDTOs = project.getTask().stream()
                            .map(task -> new TaskGetDTO(task.getTitle(), task.getStatus()))
                            .collect(Collectors.toList());
                    ProjectGetTaskDetailsDTO dto = new ProjectGetTaskDetailsDTO(taskDTOs);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
