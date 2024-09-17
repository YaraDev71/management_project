package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ProjectDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController (ProjectService projectService){
        this.projectService = projectService;
    }
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        List<ProjectDTO> projectDTOS = projectService.getAllProjects();
        return ResponseEntity.ok(projectDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO>getActivityById(@PathVariable Long id){
        ProjectDTO projectDTO =  projectService.getProjectById(id);
        if (projectDTO== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectDTO);
    }
    @PostMapping
    public ResponseEntity<ProjectDTO>createProject(@Valid @RequestBody ProjectDTO projectDTO){
        ProjectDTO createdProjectDTO = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProjectDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO>updateProject(@PathVariable Long id , @RequestBody ProjectDTO projectDTO){
        ProjectDTO updateProjectDTO = projectService.updateProject(id, projectDTO );
        return  ResponseEntity.ok(updateProjectDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>deleteProject(@PathVariable Long id ){
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


}
