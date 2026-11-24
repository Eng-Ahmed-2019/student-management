package com.ahmed.student_management.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.ahmed.student_management.dto.AuthResponseDTO;
import com.ahmed.student_management.dto.LoginRequestDTO;
import com.ahmed.student_management.service.AuthService;
import com.ahmed.student_management.dto.RegisterRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> Login(@Valid @RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(this.authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO>Register(@Valid @RequestBody RegisterRequestDTO dto){
        return ResponseEntity.ok(this.authService.register(dto));
    }
}