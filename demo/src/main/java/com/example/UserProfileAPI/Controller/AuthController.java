package com.example.UserProfileAPI.Controller;
//package com.example.userprofileapi.controller;

import com.example.UserProfileAPI.dto.*;
import com.example.UserProfileAPI.model.User;
import com.example.UserProfileAPI.Repository.UserRepository;
import com.example.UserProfileAPI.Security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error","Email already in use"));
        }
        String encoded = passwordEncoder.encode(dto.getPassword());
        // default role: ROLE_USER
        User user = new User(dto.getName(), dto.getEmail(), dto.getAge(), encoded, "ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok(java.util.Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            var userDetails = (org.springframework.security.core.userdetails.UserDetails) auth.getPrincipal();
            // get roles from user repository (or userDetails authorities)
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            List<String> roles = List.of(user.getRoles().split(","));
            String token = jwtUtil.generateToken(request.getEmail(), roles);
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(java.util.Map.of("error","Invalid credentials"));
        }
    }
}

