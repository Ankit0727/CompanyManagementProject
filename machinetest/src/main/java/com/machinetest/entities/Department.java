package com.machinetest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "is_active")
    private boolean isActive = true;
}
