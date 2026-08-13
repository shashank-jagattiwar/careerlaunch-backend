package com.careerlaunch.backend.service;

import com.careerlaunch.backend.dto.request.RegisterRequest;
import com.careerlaunch.backend.dto.response.RegisterResponse;
import com.careerlaunch.backend.dto.response.UserResponse;
import com.careerlaunch.backend.entity.User;
import com.careerlaunch.backend.enums.Role;
import com.careerlaunch.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ============================
    // REGISTER USER
    // ============================

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            return new RegisterResponse(
                    false,
                    "Email already registered!"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Hash the password before saving
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // New users are USER by default
        user.setRole(Role.USER);

        // Save user to database
        userRepository.save(user);

        return new RegisterResponse(
                true,
                "User Registered Successfully"
        );
    }

    // ============================
    // GET USER PROFILE
    // ============================

    public UserResponse getUserProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with email: " + email
                        )
                );

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}