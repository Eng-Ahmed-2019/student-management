package com.ahmed.student_management.security;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import com.ahmed.student_management.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service 
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return appUserRepository.findByUsername(username)
            .map(CustomUserDetails::buildDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}