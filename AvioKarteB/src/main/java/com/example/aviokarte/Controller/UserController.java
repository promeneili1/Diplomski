package com.example.aviokarte.Controller;


import com.example.aviokarte.dto.RegisterRequest;
import com.example.aviokarte.service.AuthenticationService;
import com.example.aviokarte.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok("Korisnik je registrovan");
    }
}