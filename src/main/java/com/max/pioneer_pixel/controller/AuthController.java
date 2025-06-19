package com.max.pioneer_pixel.controller;

import com.max.pioneer_pixel.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password) {
        LocalDateTime now = LocalDateTime.now();
        log.info("[{}] Operation: login | Params: email={}", now, email);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = jwtUtil.generateToken(email);

            log.info("[{}] Result: login successful | email={}", now, email);
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            log.warn("[{}] Error: invalid credentials for email={}", now, email);
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    private record AuthResponse(String token) {}
}
