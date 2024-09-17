package com.brahima_mamadou_yaranagore.ubd.exam_spring.repository;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject_Id(Long projectId);

    Optional <Task> findById (Long Id);
}