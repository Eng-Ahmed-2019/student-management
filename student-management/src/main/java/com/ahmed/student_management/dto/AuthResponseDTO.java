package com.ahmed.student_management.dto;

import com.ahmed.student_management.enums.Role;

public class AuthResponseDTO {

    private Role role;
    private String token;
    private String username;
    
    public AuthResponseDTO(String token, String username, Role role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}