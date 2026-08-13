package com.careerlaunch.backend.controller;

import com.careerlaunch.backend.dto.response.UserResponse;
import com.careerlaunch.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserResponse getProfile(Authentication authentication) {

        String email = authentication.getName();

        return userService.getUserProfile(email);
    }
}