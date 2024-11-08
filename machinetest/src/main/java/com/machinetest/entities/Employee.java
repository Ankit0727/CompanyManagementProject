package com.machinetest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @Lob
    private byte[] profilePicture;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
