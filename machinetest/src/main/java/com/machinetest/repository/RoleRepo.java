package com.machinetest.repository;

import com.machinetest.entities.Employee;
import com.machinetest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
