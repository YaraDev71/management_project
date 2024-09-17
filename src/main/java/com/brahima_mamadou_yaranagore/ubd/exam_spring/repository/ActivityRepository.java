package com.brahima_mamadou_yaranagore.ubd.exam_spring.repository;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByTask_Id(Long taskId);

}