package com.brahima_mamadou_yaranagore.ubd.exam_spring.service;

import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ProjectDTO;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);
    void deleteProject(Long id);
    ProjectDTO getProjectById(Long id);
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getProjectsByDateDebutBefore(Date date);
    List<ProjectDTO> getProjectsByDateFinAfter(Date date);
}