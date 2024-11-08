package com.machinetest.repository;

import com.machinetest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
