package com.careerlaunch.backend.controller;

import com.careerlaunch.backend.dto.request.LoginRequest;
import com.careerlaunch.backend.dto.request.RegisterRequest;
import com.careerlaunch.backend.dto.response.LoginResponse;
import com.careerlaunch.backend.dto.response.RegisterResponse;
import com.careerlaunch.backend.security.jwt.JwtService;
import com.careerlaunch.backend.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {

        RegisterResponse response =
                userService.register(request);

        return ResponseEntity.ok(response);
    }


    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        // Authenticate email and password
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        // Get authenticated user
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        // Generate JWT
        String token =
                jwtService.generateToken(userDetails);

        // Return JWT to client
        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}