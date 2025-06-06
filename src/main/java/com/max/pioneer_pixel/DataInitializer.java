package com.max.pioneer_pixel;

import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.DataGeneratorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final DataGeneratorService dataGeneratorService;

    public DataInitializer(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;
    }

    @PostConstruct
    public void init() {
        List<User> users = dataGeneratorService.generateUsers(10);
        users.forEach(u -> System.out.println("Generated user: " + u.getFullName() + ", age: " + u.getAge()));
        // Здесь можно сохранить пользователей в БД через UserRepository, если нужно
    }
}
