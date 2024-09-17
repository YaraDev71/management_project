package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.TaskDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> taskDTOS = taskService.getAllTasks();
        return ResponseEntity.ok(taskDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO>getTaskById(@PathVariable Long id){
        TaskDTO taskDTO =  taskService.getTaskById(id);
        if (taskDTO== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDTO);
    }
    @PostMapping
    public ResponseEntity<TaskDTO>createTask(@Valid @RequestBody TaskDTO taskDTO){
        TaskDTO createdTaskDTO = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO>updateTask(@PathVariable Long id , @RequestBody TaskDTO taskDTO){
        TaskDTO updateTaskDTO = taskService.updateTask(id, taskDTO );
        return  ResponseEntity.ok(updateTaskDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>deleteTask(@PathVariable Long id ){
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskDTO>>getTasksByUserId(@PathVariable Long projectId){
        List<TaskDTO> taskDTOS = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(taskDTOS);
    }

}


