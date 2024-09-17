package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception.ActivityNotFoundException;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.ActivityMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.ResourceMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Activity;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ActivityRepository;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {


    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final TaskRepository taskRepository;
    private final ResourceMapper resourceMapper;

    @Lazy
    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper, TaskRepository taskRepository,
                               ResourceMapper resourceMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
        this.taskRepository = taskRepository;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        if (activityDTO.getName() == null || activityDTO.getName().isEmpty()) {
            throw new IllegalArgumentException(" Le nom de l'activité ne dois pas être  null ou vide ");
        }

        Activity activity = activityMapper.toEntity(activityDTO);
        // Mapper ActivityDTO à Activity
        return activityMapper.toDTO(activityRepository.save(activity));
    }

    @Override
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new RuntimeException("Activity not found"));
        activity.setName(activityDTO.getName());
        activity.setStartDate(activityDTO.getStartDate());
        activity.setEndDate(activityDTO.getEndDate());
        activity.setStatus(activityDTO.getStatus());
        if (activityDTO.getTaskId() != null) {
            Task task = taskRepository.findById(activityDTO.getTaskId())
                    .orElseThrow(() -> new RuntimeException("Tache introuvable"));
            activity.setTask(task);
        }
        if (activityDTO.getRessources() != null) {
            activity.setRessources(resourceMapper.toEntityList(activityDTO.getRessources()));
        }
        // Mettre à jour les propriétés de activity avec activityDTO
        Activity updateActivity = activityRepository.save(activity);
        return activityMapper.toDTO(updateActivity);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
        } else {
            throw new RuntimeException(" Activité avec l'ID" + id + " est introuvable ");
        }
        //activityRepository.deleteById(id);
    }


    @Override
    public ActivityDTO getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException(" Activité avec l'ID " + id + " est introuvable "));
        return activityMapper.toDTO(activity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activityMapper.toDTOList(activities);
    }

    @Override
    public List<ActivityDTO> getActivitiesByTaskId(Long taskId) {
        List<Activity> activities = activityRepository.findByTask_Id(taskId);
        return activities.stream()
                .map(activityMapper::toDTO)
                .collect(Collectors.toList());
    }
}