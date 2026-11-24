package com.ahmed.student_management.service;

import org.springframework.stereotype.Service;
import com.ahmed.student_management.enums.Role;
import com.ahmed.student_management.model.AppUser;
import com.ahmed.student_management.security.JwtService;
import com.ahmed.student_management.dto.AuthResponseDTO;
import com.ahmed.student_management.dto.LoginRequestDTO;
import org.springframework.security.core.Authentication;
import com.ahmed.student_management.dto.RegisterRequestDTO;
import com.ahmed.student_management.security.CustomUserDetails;
import com.ahmed.student_management.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (appUserRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUser user = new AppUser(
                null,
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );

        AppUser savedUser = appUserRepository.save(user);

        UserDetails userDetails =
                CustomUserDetails.buildDetails(savedUser);

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(
                token,
                savedUser.getUsername(),
                savedUser.getRole());
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        AppUser user = appUserRepository.findByUsername(
                        userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invali username or password"));

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(
                token,
                user.getUsername(),
                user.getRole());
    }
}