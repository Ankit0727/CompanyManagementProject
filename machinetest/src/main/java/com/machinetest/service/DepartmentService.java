package com.machinetest.service;

import com.machinetest.dto.DepartmentDTO;
import com.machinetest.entities.Department;
import com.machinetest.entities.Employee;
import com.machinetest.exception.ResourceNotFoundException;
import com.machinetest.repository.DepartmentRepo;
import com.machinetest.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepository;
    private final EmployeeRepo employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepository, EmployeeRepo employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Department createDepartment(DepartmentDTO departmentDTO, String createdBy) {
        Department department = new Department();
        department.setName(departmentDTO.getName());

        // Set manager if ID is provided
        if (departmentDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(departmentDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            department.setManager(manager);
        }

        department.setCreatedBy(createdBy);
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Transactional
    public Department updateDepartment(Long id, DepartmentDTO departmentDTO, String updatedBy) {
        Department department = getDepartmentById(id);
        department.setName(departmentDTO.getName());

        // Update manager if ID is provided
        if (departmentDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(departmentDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            department.setManager(manager);
        }

        department.setLastModifiedBy(updatedBy);
        return departmentRepository.save(department);
    }

    @Transactional
    public void softDeleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        department.setActive(false);
        departmentRepository.save(department);
    }
}
