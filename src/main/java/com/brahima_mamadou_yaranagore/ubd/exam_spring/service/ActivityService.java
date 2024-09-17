package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);
    void deleteActivity(Long id);
    ActivityDTO getActivityById(Long id);
    List<ActivityDTO> getAllActivities();
    List<ActivityDTO> getActivitiesByTaskId(Long taskId);

}