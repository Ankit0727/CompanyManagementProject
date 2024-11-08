package com.machinetest.controller;

import com.machinetest.dto.EmployeeDTO;
import com.machinetest.entities.Employee;
import com.machinetest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO,
                                                   @RequestHeader("Authorization") String token) {
        String createdBy = extractUsernameFromToken(token);
        Employee employee = employeeService.createEmployee(employeeDTO, createdBy);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody EmployeeDTO employeeDTO,
                                                   @RequestHeader("Authorization") String token) {
        String updatedBy = extractUsernameFromToken(token);
        Employee employee = employeeService.updateEmployee(id, employeeDTO, updatedBy);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    private String extractUsernameFromToken(String token) {
        // Implement JWT token parsing to extract username
        return token; // Placeholder, implement your JWT logic here
    }
}
