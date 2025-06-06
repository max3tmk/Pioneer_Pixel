package com.max.pioneer_pixel.service;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataGeneratorService {

    private final Faker faker = new Faker();
    private final Random random = new Random();

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            User user = new User();

            // Генерируем имя
            user.setName(faker.name().fullName());

            // Генерируем дату рождения (от 18 до 70 лет)
            int age = 18 + random.nextInt(53);
            LocalDate dob = LocalDate.now().minusYears(age).minusDays(random.nextInt(365));
            user.setDateOfBirth(dob);

            // Устанавливаем пароль (просто рандомный текст с длиной >= 8)
            user.setPassword(faker.internet().password(8, 16));

            users.add(user);
        }

        return users;
    }

    // Можно добавить метод для вычисления возраста, если нужно:
    public int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
