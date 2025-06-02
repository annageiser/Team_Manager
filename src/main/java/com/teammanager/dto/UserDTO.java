package com.teammanager.dto;

import com.teammanager.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    // Exclude sensitive information like password
    // Exclude complex relationships to prevent circular references
} 