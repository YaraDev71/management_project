package com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.TaskDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final ProjectRepository projectRepository;
    private final  ActivityMapper activityMapper;
    @Lazy
     @Autowired
    public TaskMapper(  ProjectRepository projectRepository, ActivityMapper activityMapper) {
        this.projectRepository = projectRepository;
        this.activityMapper = activityMapper;
    }


    public TaskDTO toDTO(Task task){
        if (task == null){
            return null;}
         return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .priority(task.getPriority())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .projectId(task.getProject() != null ? task.getProject().getId(): null)
               //  .activites(activityMapper.toDTOList(task.getActivites()))
                 .build();


    }

    public Task toEntity(TaskDTO taskDTO) {
        return Task.builder()
                .name(taskDTO.getName())
                .priority(taskDTO.getPriority())
                .startDate(taskDTO.getStartDate())
                .endDate(taskDTO.getEndDate())
                .project(projectRepository.findById(taskDTO.getProjectId()).orElse(null))
               // .activites(activityMapper.toEntityList(taskDTO.getActivites()))
                .build();
    }

}

