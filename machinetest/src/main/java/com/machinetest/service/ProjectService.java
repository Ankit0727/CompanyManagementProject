package com.machinetest.service;

import com.machinetest.dto.ProjectDTO;
import com.machinetest.entities.Department;
import com.machinetest.entities.Project;
import com.machinetest.repository.DepartmentRepo;
import com.machinetest.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Transactional
    public Project createProject(ProjectDTO projectDTO) {
        Department department = departmentRepository.findById(projectDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Project project = Project.builder()
                .name(projectDTO.getName())
                .department(department)
                .isActive(true)
                .build();

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id).filter(Project::isActive);
    }

    @Transactional
    public Project updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Department department = departmentRepository.findById(projectDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        project.setName(projectDTO.getName());
        project.setDepartment(department);
        return projectRepository.save(project);
    }

    @Transactional
    public void softDeleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        project.setActive(false);
        projectRepository.save(project);
    }
}
