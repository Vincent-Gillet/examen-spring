package com.taskmanager.api.service;

import com.taskmanager.api.model.Project;
import com.taskmanager.api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        project.setId(id);
        return projectRepository.save(project);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean existsProjectById(Long id) {
        return projectRepository.existsById(id);
    }

}
