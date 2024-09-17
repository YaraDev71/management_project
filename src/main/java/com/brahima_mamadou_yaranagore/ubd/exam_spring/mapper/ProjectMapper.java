package com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ProjectDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Project;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Component
public class ProjectMapper {
    private final TaskRepository taskRepository;

    @Lazy
    @Autowired
    public ProjectMapper(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .taskIds(project.getTasks().stream()
                        .map(Task::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public Project toEntity(ProjectDTO projectDTO) {
        return Project.builder()
                .id(projectDTO.getId())
                .name(projectDTO.getName())
                .startDate(projectDTO.getStartDate())
                .endDate(projectDTO.getEndDate())
                .description(projectDTO.getDescription())
                .tasks(taskRepository.findAllById(projectDTO.getTaskIds()))
                .build();
    }

}