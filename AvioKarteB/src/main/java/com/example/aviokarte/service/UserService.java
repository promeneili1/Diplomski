package com.example.aviokarte.service;

import com.example.aviokarte.dto.RegisterRequest;
import com.example.aviokarte.enums.UserRole;
import com.example.aviokarte.model.User;
import com.example.aviokarte.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public String register(RegisterRequest registerRequest) {
        System.out.println("Registering user: " + registerRequest.getUsername());
        if (repository.existsByUsername(registerRequest.getUsername())) {
            return "Username already exists";  // Poruka za postojeći username
        }
        if (repository.existsByEmail(registerRequest.getEmail())) {
            return "Email already exists";  // Poruka za postojeću email adresu
        }
        if (repository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            return "Phone number already exists";  // Poruka za postojeći broj telefona
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setEmail(registerRequest.getEmail());
        user.setRole(UserRole.USER);

        repository.save(user);

        return "User registered successfully";  // Poruka za uspešnu registraciju
    }
}

