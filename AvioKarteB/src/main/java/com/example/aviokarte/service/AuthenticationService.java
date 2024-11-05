package com.example.aviokarte.service;

import com.example.aviokarte.dto.AuthenticationResponse;
import com.example.aviokarte.dto.LoginRequest;
import com.example.aviokarte.model.User;
import com.example.aviokarte.repository.UserRepository;
import com.example.aviokarte.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(LoginRequest request) {
        // Retrieve the user from the database
        User user = repository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) {
            System.out.println("Username not found: " + request.getUsername());
            return "Username not found";
        }

        // Check password match
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("Incorrect password for user: " + request.getUsername());
            return "Incorrect password";
        }

        // Attempt authentication
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials exception: " + e.getMessage());
            return "Authentication failed";
        }

        // Generate JWT token
        String token = jwtService.generateToken(user);
        System.out.println("Generated token for user: " + request.getUsername());
        return "Login successful, token: " + token;
    }

}