package com.brahima_mamadou_yaranagore.ubd.exam_spring.repository;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByActivity_Id(Long activityId);
    List<Resource> findByType(String type);
}
