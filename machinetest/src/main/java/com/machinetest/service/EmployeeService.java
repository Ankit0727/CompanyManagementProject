package com.machinetest.service;

import com.machinetest.dto.EmployeeDTO;
import com.machinetest.entities.Employee;
import com.machinetest.entities.Department;
import com.machinetest.entities.Role;
import com.machinetest.exception.ResourceNotFoundException;
import com.machinetest.repository.EmployeeRepo;
import com.machinetest.repository.DepartmentRepo;
import com.machinetest.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepository;
    private final DepartmentRepo departmentRepository;
    private final RoleRepo roleRepository;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepository,
                           DepartmentRepo departmentRepository,
                           RoleRepo roleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Employee createEmployee(EmployeeDTO employeeDTO, String createdBy) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setProfilePicture(employeeDTO.getProfilePicture());

        // Set department and role entities based on IDs
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Role role = roleRepository.findById(employeeDTO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        employee.setDepartment(department);
        employee.setRole(role);
        employee.setCreatedBy(createdBy);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO, String updatedBy) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        existingEmployee.setProfilePicture(employeeDTO.getProfilePicture());

        // Update department and role if necessary
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Role role = roleRepository.findById(employeeDTO.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        existingEmployee.setDepartment(department);
        existingEmployee.setRole(role);
        existingEmployee.setLastModifiedBy(updatedBy);
        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
