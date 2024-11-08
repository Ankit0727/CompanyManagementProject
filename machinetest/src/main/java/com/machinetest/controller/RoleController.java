package com.machinetest.controller;

import com.machinetest.dto.RoleDTO;
import com.machinetest.entities.Role;
import com.machinetest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO, @RequestHeader("Authorization") String token) {
        String createdBy = extractUsernameFromToken(token);
        Role role = roleService.createRole(roleDTO, createdBy);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.of(roleService.getRoleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO, @RequestHeader("Authorization") String token) {
        String updatedBy = extractUsernameFromToken(token);
        Role role = roleService.updateRole(id, roleDTO, updatedBy);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    private String extractUsernameFromToken(String token) {
        // Implement JWT token parsing to extract the username
        return token;
    }
}
