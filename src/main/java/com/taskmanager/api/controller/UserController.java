package com.taskmanager.api.controller;

import com.taskmanager.api.dto.ProjectDTO;
import com.taskmanager.api.dto.ProjectGetDTO;
import com.taskmanager.api.dto.UserDTO;
import com.taskmanager.api.dto.UserGetDTO;
import com.taskmanager.api.model.Project;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.User;
import com.taskmanager.api.service.ProjectService;
import com.taskmanager.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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

    private final ProjectService projectService;


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
    @Transactional(readOnly = true)
    public ResponseEntity<Set<ProjectGetDTO>> getAllProjectsOfUser(@PathVariable Long id) {
        Set<ProjectGetDTO> projectDTOs = projectService.getAllProjectsOfUser(id);
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Set<Task>> getAllUserTasks(@PathVariable Long id) {
        Set<Task> projects = userService.getUserById(id)
                .map(User::getTask)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(projects);
    }


}
