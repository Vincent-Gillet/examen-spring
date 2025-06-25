package com.taskmanager.api.service;

import com.taskmanager.api.dto.*;
import com.taskmanager.api.model.Project;
import com.taskmanager.api.model.User;
import com.taskmanager.api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<ProjectGetDTO> getAllProjectsOfUser(Long userId) {
        return projectRepository.findAllByCreator_Id(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public ProjectGetDTO toDTO(Project project) {
        if (project == null) return null;
        return new ProjectGetDTO(
                project.getName()
        );
    }

    public Project toEntity(ProjectDTO dto) {
        if (dto == null) return null;
        Project project = new Project();
        project.setName(dto.getName());
        return project;
    }


    public ProjectCreateDTO toDTOCreate(Project project) {
        if (project == null) return null;
        ProjectCreateDTO dto = new ProjectCreateDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        User creator = project.getCreator();
        if (creator != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(creator.getId());
            userDTO.setUsername(creator.getUsername());
            dto.setCreator(userDTO);
        }
        return dto;
    }

}
