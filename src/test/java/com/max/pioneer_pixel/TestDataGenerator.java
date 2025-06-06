package com.max.pioneer_pixel;

import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.DataGeneratorService;

import java.util.List;

public class TestDataGenerator {

    public static void main(String[] args) {
        DataGeneratorService generator = new DataGeneratorService();

        List<User> users = generator.generateUsers(5);

        for (User user : users) {
            System.out.println("User fullName: " + user.getName());
            System.out.println("Date of birth: " + user.getDateOfBirth());
            System.out.println("Age: " + user.getAge());
            System.out.println("Password: " + user.getPassword());
            System.out.println("------------");
        }
    }
}
