package com.machinetest.repository;

import com.machinetest.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
