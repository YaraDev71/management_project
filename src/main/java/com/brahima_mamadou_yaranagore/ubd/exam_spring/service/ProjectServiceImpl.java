package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ProjectDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception.ProjectNotFoundException;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.ProjectMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Project;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ProjectRepository;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private  final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper ;
    private final TaskRepository taskRepository;
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper,
                               TaskRepository taskRepository){
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.taskRepository = taskRepository;
    }


    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        try {
            Project savedProject = projectRepository.save(project);
            return projectMapper.toDTO(savedProject);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erreur d'enregistrement du projet :violation de l'integrité des données ", ex);
        }
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projet introuvable avec l'ID  " + id));
        project.setName(projectDTO.getName());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setDescription(projectDTO.getDescription());

        if (projectDTO.getTaskIds() != null) {
            List<Task> tasks = projectDTO.getTaskIds().stream()
                    .map(taskId -> taskRepository.findById(taskId)
                            .orElseThrow(() -> new EntityNotFoundException("Tache introuvable avec l'ID " + taskId)))
                    .collect(Collectors.toList());
            project.setTasks(tasks); // Assuming there's a setTasks method in Project
        }

        try {
            Project updatedProject = projectRepository.save(project);
            return projectMapper.toDTO(updatedProject);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erreur de mise a jour du projet : violation de l'integrité des données", ex);
        }
    }
    @Transactional
    @Override
    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            try {
                projectRepository.deleteById(id);
            } catch (Exception ex) {
                throw new RuntimeException("Erreur de suppression de projet avec l'ID " + id, ex);
            }
        } else {
            throw new EntityNotFoundException("Projet avec l'ID " + id + " est introuvable");
        }
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(" Projet avec l'ID  " + id + " est introuvable "));
        return projectMapper.toDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<ProjectDTO> getProjectsByDateDebutBefore(Date date) {
        List<Project> projects = projectRepository.findByStartDate(date);
        return projects.stream().map(projectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getProjectsByDateFinAfter(Date date) {
        List<Project> projects = projectRepository.findByEndDate(date);
        return projects.stream().map(projectMapper::toDTO).collect(Collectors.toList());
    }

}