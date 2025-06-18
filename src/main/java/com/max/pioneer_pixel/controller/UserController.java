package com.max.pioneer_pixel.controller;

import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/API/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Map<String, Object> userMap) {
        // Оставляем существующую логику создания пользователя
        throw new UnsupportedOperationException("Use your existing createUser method");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> resultPage = userService.searchUsers(name, email, phone, dateOfBirth, pageable);
        return ResponseEntity.ok(resultPage);
    }

    @PostMapping("/addEmail")
    public ResponseEntity<String> addEmail(@RequestParam Long userId, @RequestParam String email) {
        try {
            userService.addEmail(userId, email);
            return ResponseEntity.ok("Email added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteEmail")
    public ResponseEntity<String> deleteEmail(@RequestParam Long userId, @RequestParam String email) {
        userService.deleteEmail(userId, email);
        return ResponseEntity.ok("Email deleted successfully");
    }

    @PostMapping("/addPhone")
    public ResponseEntity<String> addPhone(@RequestParam Long userId, @RequestParam String phone) {
        try {
            userService.addPhone(userId, phone);
            return ResponseEntity.ok("Phone added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletePhone")
    public ResponseEntity<String> deletePhone(@RequestParam Long userId, @RequestParam String phone) {
        userService.deletePhone(userId, phone);
        return ResponseEntity.ok("Phone deleted successfully");
    }

    @GetMapping("/emails")
    public List<String> getEmails(@RequestParam Long userId) {
        return userService.getEmails(userId);
    }

    @GetMapping("/phones")
    public List<String> getPhones(@RequestParam Long userId) {
        return userService.getPhones(userId);
    }
}
