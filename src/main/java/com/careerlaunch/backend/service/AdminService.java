package com.careerlaunch.backend.service;

import com.careerlaunch.backend.dto.response.UserResponse;
import com.careerlaunch.backend.entity.User;
import com.careerlaunch.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }
}