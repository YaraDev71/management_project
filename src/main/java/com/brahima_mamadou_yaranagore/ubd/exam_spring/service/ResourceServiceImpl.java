package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ResourceDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception.ResourceNotFoundException;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper.ResourceMapper;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Activity;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Resource;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ActivityRepository;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final ActivityRepository activityRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper,
                               ActivityRepository activityRepository){
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
        this.activityRepository = activityRepository;
    }
    @Override
    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource.setCreate_date(LocalDate.now());
        resource.setCreate_date(LocalDate.now());

            if (resourceDTO.getActivityId() != null) {
                Activity activity = activityRepository.findById(resourceDTO.getActivityId())
                        .orElseThrow(() -> new EntityNotFoundException("Activité introuvable avec l'ID " + resourceDTO.getActivityId()));
                resource.setActivity(activity);
            } else {
                throw new IllegalArgumentException("L' ID de l'Activité ne doit pas etre null");
            }

            try {
                Resource savedResource = resourceRepository.save(resource);
                return resourceMapper.toDTO(savedResource);
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException("Erreur d'enregistrement de la ressource: Data integrity violation", ex);
            }
    }


    @Override
    public ResourceDTO updateResource(Long id, ResourceDTO resourceDTO) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ressource introuvable avec l'ID " + id));

        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        resource.setUrl(resourceDTO.getUrl());
        resource.setCreate_date(resourceDTO.getCreate_date());

        if (resourceDTO.getActivityId() != null) {
            Activity activity = activityRepository.findById(resourceDTO.getActivityId())
                    .orElseThrow(() -> new EntityNotFoundException("Activité introuvable avec l'ID " + resourceDTO.getActivityId()));
            resource.setActivity(activity);
        }

        try {
            Resource updatedResource = resourceRepository.save(resource);
            return resourceMapper.toDTO(updatedResource);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException(" Erreur de mise a jour de la ressource: violation de l'integrité des données ", ex);
        }
    }


    @Transactional
    @Override
    public void deleteResource(Long id) {
        if (resourceRepository.existsById(id)) {
            try {
                resourceRepository.deleteById(id);
            } catch (Exception ex) {
                throw new RuntimeException("Erreur de suppression de la  ressource avec l'ID " + id, ex);
            }
        } else {
            throw new EntityNotFoundException("Ressource avec l'ID " + id + " est introuvable");
        }
    }

    @Override
    public ResourceDTO getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" Ressource introuvable avec l'ID :  " + id));
        return resourceMapper.toDTO(resource);
    }
    @Override
    public List<ResourceDTO> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream()
                .map(resourceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getResourcesByActivityId(Long activityId) {
        List<Resource> resources = resourceRepository.findByActivity_Id(activityId);
        return resources.stream()
                .map(resourceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getResourcesByType(String type) {
        List<Resource> resources = resourceRepository.findByType(type);
        return resources.stream()
                .map(resourceMapper::toDTO)
                .collect(Collectors.toList());
    }
}