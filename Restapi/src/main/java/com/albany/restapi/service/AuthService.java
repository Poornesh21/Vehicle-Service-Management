// Restapi/src/main/java/com/albany/restapi/service/AuthService.java
package com.albany.restapi.service;

import com.albany.restapi.dto.LoginRequest;
import com.albany.restapi.dto.LoginResponse;
import com.albany.restapi.model.User;
import com.albany.restapi.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        // Find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        // For simplicity, we'll do a direct password comparison
        // In a real app, you'd use passwordEncoder.matches()
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!user.getIsActive()) {
            throw new BadCredentialsException("User account is inactive");
        }

        // Return basic user info on successful login
        return new LoginResponse(
                user.getUserId(),
                user.getEmail(),
                user.getRole().toString(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}