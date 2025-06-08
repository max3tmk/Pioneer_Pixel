package com.max.pioneer_pixel.controller;

import com.max.pioneer_pixel.dto.UserRequestDto;
import com.max.pioneer_pixel.dto.UserResponseDto;
import com.max.pioneer_pixel.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto) {
        UserResponseDto createdUser = userService.createUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
