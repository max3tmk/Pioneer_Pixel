package com.max.pioneer_pixel.controller;

import com.max.pioneer_pixel.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/email")
    public ResponseEntity<String> loginByEmail(@RequestBody LoginRequest request) {
        try {
            String token = authService.loginByEmail(request.getLogin(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login/phone")
    public ResponseEntity<String> loginByPhone(@RequestBody LoginRequest request) {
        try {
            String token = authService.loginByPhone(request.getLogin(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Data
    static class LoginRequest {
        private String login; // email или phone
        private String password;
    }
}
