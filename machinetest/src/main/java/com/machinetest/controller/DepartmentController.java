package com.machinetest.controller;

import com.machinetest.dto.DepartmentDTO;
import com.machinetest.entities.Department;
import com.machinetest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO,
                                                       @RequestHeader("Authorization") String token) {
        String createdBy = extractUsernameFromToken(token);
        Department department = departmentService.createDepartment(departmentDTO, createdBy);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                                       @RequestBody DepartmentDTO departmentDTO,
                                                       @RequestHeader("Authorization") String token) {
        String updatedBy = extractUsernameFromToken(token);
        Department department = departmentService.updateDepartment(id, departmentDTO, updatedBy);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteDepartment(@PathVariable Long id) {
        departmentService.softDeleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    private String extractUsernameFromToken(String token) {
        // Implement JWT token parsing to extract username
        return token; // Placeholder, implement your JWT logic here
    }
}
