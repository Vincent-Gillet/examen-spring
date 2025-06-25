package com.taskmanager.api.controller;

import com.taskmanager.api.model.Project;
import com.taskmanager.api.model.Task;
import com.taskmanager.api.model.User;
import com.taskmanager.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<Set<Project>> getAllUserProjects(@PathVariable Long id) {
        Set<Project> projects = userService.getUserById(id)
                .map(User::getProjects)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Set<Task>> getAllUserTasks(@PathVariable Long id) {
        Set<Task> projects = userService.getUserById(id)
                .map(User::getTask)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return ResponseEntity.ok(projects);
    }


}
