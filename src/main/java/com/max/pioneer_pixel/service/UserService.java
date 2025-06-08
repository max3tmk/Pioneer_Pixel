package com.max.pioneer_pixel.service;

import com.max.pioneer_pixel.dto.UserRequestDto;
import com.max.pioneer_pixel.dto.UserResponseDto;
import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();
}
