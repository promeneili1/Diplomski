package com.example.aviokarte.Controller;

import com.example.aviokarte.dto.LoginRequest;
import com.example.aviokarte.dto.RegisterRequest;
import com.example.aviokarte.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String responseMessage = authenticationService.authenticate(request);
        return ResponseEntity.ok(responseMessage); // Return the response message
    }
}

