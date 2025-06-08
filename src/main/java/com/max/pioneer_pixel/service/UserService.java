package com.max.pioneer_pixel.service;

import com.max.pioneer_pixel.dto.UserRequestDto;
import com.max.pioneer_pixel.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();

    Optional<UserResponseDto> getUserById(Long id);

    Optional<UserResponseDto> updateUser(Long id, UserRequestDto dto);

    boolean deleteUser(Long id);
}
