package com.max.pioneer_pixel;

import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.UserDataGenerator;

import java.util.List;

public class TestDataGenerator {

    private final UserDataGenerator generator = new UserDataGenerator();

    public void runTest() {
        List<User> users = generator.generateUsers(5);
        for (User user : users) {
            System.out.println("User name: " + user.getName());
            System.out.println("User email: " + user.getEmail());
            System.out.println("User dateOfBirth: " + user.getDateOfBirth());
            System.out.println("User password: " + user.getPassword());
            System.out.println("-----");
        }
    }

    public static void main(String[] args) {
        new TestDataGenerator().runTest();
    }
}
