package com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Activity;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityMapper {
    private final TaskRepository taskRepository;
    private final ResourceMapper resourceMapper;
    @Lazy
    @Autowired
   public ActivityMapper(  TaskRepository taskRepository, ResourceMapper resourceMapper) {
        this.taskRepository = taskRepository;
        this.resourceMapper = resourceMapper;
    }

    public ActivityDTO toDTO(Activity activity){
        if (activity== null){
            return null;
        }
        return ActivityDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .status(activity.getStatus())
                .taskId(activity.getTask() != null ? activity.getTask().getId(): null).build();
                //.ressources(resourceMapper.toDTOList(activity.getRessources()))

    }
    public Activity toEntity(ActivityDTO activityDTO){
        return Activity.builder()
                .name(activityDTO.getName())
                .startDate(activityDTO.getStartDate())
                .endDate(activityDTO.getEndDate())
                .status(activityDTO.getStatus())
               .task(taskRepository.findById(activityDTO.getTaskId()).orElse(null)).build();
                //.ressources(resourceMapper.toEntityList(activityDTO.getRessources())).build();

    }

    public List<ActivityDTO> toDTOList(List<Activity> activities) {
        return activities.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public List<Activity> toEntityList(List<ActivityDTO> activityDTOs) {
        return activityDTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
