package com.brahima_mamadou_yaranagore.ubd.exam_spring.mapper;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ResourceDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Resource;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceMapper {
    private final ActivityRepository activityRepository;

    @Autowired
    public ResourceMapper(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ResourceDTO toDTO(Resource resource){
        if (resource == null) {
            return null;
        }
        return ResourceDTO.builder()
                .id(resource.getId())
                .name(resource.getName())
                .type(resource.getType())
                .url(resource.getUrl())
                .create_date(resource.getCreate_date())
                .activityId(resource.getActivity()!= null ? resource.getActivity().getId(): null)
                .build();
    }

    public Resource toEntity(ResourceDTO resourceDTO) {
        return Resource.builder()
                .name(resourceDTO.getName())
                .type(resourceDTO.getType())
                .url(resourceDTO.getUrl())
                .create_date(resourceDTO.getCreate_date())
                .activity(activityRepository.findById(resourceDTO.getActivityId()).orElse(null))
                .build();
    }


    // Conversion d'une liste de ResourceDTO en liste de Resource
    public List<Resource> toEntityList(List<ResourceDTO> resourceDTOs) {
        return resourceDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    // Conversion d'une liste de Resource en liste de ResourceDTO
    public List<ResourceDTO> toDTOList(List<Resource> resources) {
        return resources.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
