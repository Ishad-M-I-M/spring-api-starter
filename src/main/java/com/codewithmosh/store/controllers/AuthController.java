package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.LoginRequest;
import com.codewithmosh.store.exceptions.InvalidCredentialsException;
import com.codewithmosh.store.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request
    ) {
        authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Void> handleInvalidCredentials() {
        return ResponseEntity.status(401).build();
    }
}
