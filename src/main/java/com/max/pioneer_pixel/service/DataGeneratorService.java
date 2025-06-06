package com.max.pioneer_pixel.service;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataGeneratorService {

    private final Faker faker = new Faker();

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            User user = new User();

            user.setFullName(faker.name().fullName());
            user.setDateOfBirth(faker.date().birthday()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            user.setPassword(faker.internet().password(8, 16));

            // Вычислим возраст на основе dateOfBirth
            int age = LocalDate.now().getYear() - user.getDateOfBirth().getYear();
            user.setAge(age);

            users.add(user);
        }

        return users;
    }
}
