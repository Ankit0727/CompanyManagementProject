package com.machinetest.controller;

import com.machinetest.dto.ProjectDTO;
import com.machinetest.entities.Project;
import com.machinetest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO, @RequestHeader("Authorization") String token) {
        String createdBy = extractUsernameFromToken(token);
        Project project = projectService.createProject(projectDTO);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO, @RequestHeader("Authorization") String token) {
        String updatedBy = extractUsernameFromToken(token);
        Project project = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteProject(@PathVariable Long id) {
        projectService.softDeleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private String extractUsernameFromToken(String token) {
        // Implement JWT token parsing to extract username
        return token;
    }
}
