package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.TaskDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception.TaskNotFoundException;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.ActivityMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.TaskMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Project;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ProjectRepository;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ActivityMapper activityMapper;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ActivityMapper activityMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.activityMapper = activityMapper;
        this.projectRepository = projectRepository;
    }


    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);

        try {
            Task savedTask = taskRepository.save(task);
            return taskMapper.toDTO(savedTask);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erreur d'enregistrement de la tache: violation de l'integrité des données ", ex);
        }
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tache introuvable avec l'ID " + id));

        task.setName(taskDTO.getName());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setPriority(taskDTO.getPriority());

        if (taskDTO.getProjectId() != null) {
            Project project = projectRepository.findById(taskDTO.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Projet introuvable avec l'ID " + taskDTO.getProjectId()));
            task.setProject(project);
        }

        if (taskDTO.getActivites() != null) {
            task.setActivites(activityMapper.toEntityList(taskDTO.getActivites()));
        }

        try {
            Task updatedTask = taskRepository.save(task);
            return taskMapper.toDTO(updatedTask);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erreur de mise jour de la tache : violation de l'integrité des données ", ex);
        }
    }

    @Transactional
    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tache introuvable avec l'ID " + id));

        try {
            taskRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Erreur de suppression de l'ID " + id, ex);
        }
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tache  avec l'ID" + id + " est introuvable"));
        return taskMapper.toDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_Id(projectId);
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }
}