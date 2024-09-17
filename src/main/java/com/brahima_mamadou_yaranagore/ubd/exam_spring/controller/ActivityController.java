package com.brahima_mamadou_yaranagore.ubd.exam_spring.controller;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ActivityDTO;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.service.ActivityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activites")
public class ActivityController {

    private final ActivityService activityService;
    @Lazy
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;

    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities(){
    List<ActivityDTO> activityDTOS = activityService.getAllActivities();
        return ResponseEntity.ok(activityDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO>getActivityById(@PathVariable Long id){
        ActivityDTO activityDTO =  activityService.getActivityById(id);
        if (activityDTO== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activityDTO);
    }
    @PostMapping
    public ResponseEntity<ActivityDTO>createActivity(@Valid @RequestBody ActivityDTO activityDTO){
        ActivityDTO createdActivityDTO = activityService.createActivity(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO>updateActivity(@PathVariable Long id , @RequestBody ActivityDTO activityDTO){
        ActivityDTO updateActivityDTO = activityService.updateActivity(id, activityDTO );
      return  ResponseEntity.ok(updateActivityDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>deleteActivity(@PathVariable Long id ){
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByTaskId(@PathVariable Long taskId){
        List<ActivityDTO> activityDTOS = activityService.getActivitiesByTaskId(taskId);
        return ResponseEntity.ok(activityDTOS);
    }


}
