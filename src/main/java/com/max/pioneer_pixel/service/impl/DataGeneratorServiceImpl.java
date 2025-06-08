package com.max.pioneer_pixel.service.impl;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.dto.UserRequestDto;
import com.max.pioneer_pixel.service.DataGeneratorService;
import com.max.pioneer_pixel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DataGeneratorServiceImpl implements DataGeneratorService {

    private final UserService userService;
    private final Faker faker = new Faker();

    @Override
    public void generateTestData(int count) {
        for (int i = 0; i < count; i++) {
            UserRequestDto dto = new UserRequestDto();

            dto.setName(faker.name().fullName());
            dto.setPassword(faker.internet().password(8, 16));
            dto.setEmail(faker.internet().emailAddress());
            dto.setDateOfBirth(faker.date().birthday().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate());

            // Правильно задаём initialBalance через BigDecimal
            dto.setInitialBalance(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 10000)));

            userService.createUser(dto);
        }
    }
}
