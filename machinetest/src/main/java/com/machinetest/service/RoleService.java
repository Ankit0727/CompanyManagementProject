package com.machinetest.service;

import com.machinetest.dto.RoleDTO;
import com.machinetest.entities.Role;
import com.machinetest.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepository;

    public Role createRole(RoleDTO roleDTO, String createdBy) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return roleRepository.save(role);  // `createdBy` is handled by Spring auditing
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role updateRole(Long id, RoleDTO roleDTO, String updatedBy) {
        Role role = roleRepository.findById(id).orElseThrow();
        role.setName(roleDTO.getName());
        return roleRepository.save(role);  // `updatedBy` is handled by Spring auditing
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
