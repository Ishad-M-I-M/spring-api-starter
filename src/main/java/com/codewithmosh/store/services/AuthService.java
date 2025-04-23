package com.codewithmosh.store.services;

import com.codewithmosh.store.exceptions.InvalidCredentialsException;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void login(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(InvalidCredentialsException::new);
        if (! passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
    }
}
