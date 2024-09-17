package com.brahima_mamadou_yaranagore.ubd.exam_spring.repository;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStartDate(Date date);
    List<Project> findByEndDate(Date date);

//    Optional<Project> findById (Long id);
}

