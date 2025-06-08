package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.dto.UserRequestDto;
import com.max.pioneer_pixel.dto.UserResponseDto;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.AccountService;
import com.max.pioneer_pixel.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AccountService accountService;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();

        user = userDao.save(user);
        accountService.createInitialAccount(user.getId(), dto.getInitialBalance());
        return mapToResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDto> getUserById(Long id) {
        return userDao.findById(id)
                .map(this::mapToResponseDto);
    }

    @Override
    @Transactional
    public Optional<UserResponseDto> updateUser(Long id, UserRequestDto dto) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        User user = optionalUser.get();
        user.setName(dto.getName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());

        user = userDao.save(user);
        return Optional.of(mapToResponseDto(user));
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if (!userDao.existsById(id)) {
            return false;
        }
        userDao.deleteById(id);
        return true;
    }

    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
