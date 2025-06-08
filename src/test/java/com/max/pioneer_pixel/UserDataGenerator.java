package com.max.pioneer_pixel;

import com.max.pioneer_pixel.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataGenerator {

    private final Random random = new Random();

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setEmail("user" + i + "@example.com");
            user.setDateOfBirth(LocalDate.of(1990 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28)));
            user.setPassword("pass" + i);
            users.add(user);
        }
        return users;
    }
}
