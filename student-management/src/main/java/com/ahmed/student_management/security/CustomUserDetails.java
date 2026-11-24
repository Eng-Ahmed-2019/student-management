package com.ahmed.student_management.security;

import com.ahmed.student_management.model.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails {

    public static UserDetails buildDetails(AppUser user){
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}