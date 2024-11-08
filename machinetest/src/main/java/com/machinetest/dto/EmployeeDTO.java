package com.machinetest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private String email;
    private byte[] profilePicture;
    private Long departmentId;
    private Long roleId;
}
