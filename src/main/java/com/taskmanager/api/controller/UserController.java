package com.taskmanager.api.controller;

import com.taskmanager.api.dto.*;
import com.taskmanager.api.model.User;
import com.taskmanager.api.service.ProjectService;
import com.taskmanager.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour la gestion des utilisateurs.
 * Expose les endpoints pour les opérations CRUD sur les utilisateurs.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(userService.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<UserGetProjectDTO> getAllProjectsOfUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> {
                    user.getProjects().size();
                    List<ProjectGetDTO> projectDTOs = user.getProjects().stream()
                            .map(project -> new ProjectGetDTO(project.getName()))
                            .collect(Collectors.toList());
                    UserGetProjectDTO dto = new UserGetProjectDTO(user.getUsername(), projectDTOs);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<UserGetTaskDTO> getAllTasksOfUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> {
                    user.getTask().size();
                    List<TaskDTO> taskDTOs = user.getTask().stream()
                            .map(task -> new TaskDTO(task.getId(), task.getTitle()))
                            .collect(Collectors.toList());
                    UserGetTaskDTO dto = new UserGetTaskDTO(user.getUsername(), taskDTOs);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
