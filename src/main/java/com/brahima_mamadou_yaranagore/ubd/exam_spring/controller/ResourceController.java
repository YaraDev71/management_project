package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ResourceDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.ResourceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ressources")
public class ResourceController {
    private final ResourceService resourceService;
    @Autowired
    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;

    }


    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources(){
        List<ResourceDTO> resourceDTOS = resourceService.getAllResources();
        return ResponseEntity.ok(resourceDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO>getResourceById(@PathVariable Long id){
        ResourceDTO resourceDTO =  resourceService.getResourceById(id);
        if (resourceDTO== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resourceDTO);
    }
    @PostMapping
    public ResponseEntity<ResourceDTO>createResource(@Valid @RequestBody ResourceDTO resourceDTO){
        ResourceDTO createdResourceDTO = resourceService.createResource(resourceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResourceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO>updateResource(@PathVariable Long id , @RequestBody ResourceDTO resourceDTO){
        ResourceDTO updateResourceDTO = resourceService.updateResource(id, resourceDTO );
        return  ResponseEntity.ok(updateResourceDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>deleteResource(@PathVariable Long id ){
        try {
            resourceService.deleteResource(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<ResourceDTO>> getResourcesByActivityId(@PathVariable Long activityId){
        List<ResourceDTO> resourceDTOS = resourceService.getResourcesByActivityId(activityId);
        return ResponseEntity.ok(resourceDTOS);
    }



}
