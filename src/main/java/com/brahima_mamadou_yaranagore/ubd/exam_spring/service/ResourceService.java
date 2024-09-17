package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ResourceDTO;

import java.util.List;

public interface ResourceService {
    ResourceDTO createResource(ResourceDTO resourceDTO);
    ResourceDTO updateResource(Long id, ResourceDTO resourceDTO);
    void deleteResource(Long id);
    ResourceDTO getResourceById(Long id);
    List<ResourceDTO> getAllResources();
    List<ResourceDTO> getResourcesByActivityId(Long activityId);
    List<ResourceDTO> getResourcesByType(String type);
}