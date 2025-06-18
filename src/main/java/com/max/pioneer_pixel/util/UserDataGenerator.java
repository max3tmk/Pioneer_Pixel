package com.max.pioneer_pixel.util;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.EmailDataService;
import com.max.pioneer_pixel.service.PhoneDataService;
import com.max.pioneer_pixel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class UserDataGenerator {

    private final Faker faker = new Faker();
    private final Random random = new Random();

    private final UserService userService;
    private final EmailDataService emailDataService;
    private final PhoneDataService phoneDataService;

    public void generateAndInsertUsers(int count) {
        for (int i = 1; i <= count; i++) {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String phone = generatePhone();
            String password = "password_" + i; // пароли в нужном формате
            LocalDate dob = generateDateOfBirth();
            BigDecimal balance = generateInitialBalance();

            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setDateOfBirth(dob);

            userService.createUserWithAccount(user, balance); // сохраняем user + account
            emailDataService.addEmailData(user.getId(), email);
            phoneDataService.addPhoneData(user.getId(), phone);
        }
    }

    private String generatePhone() {
        return "3752" + (1000000 + random.nextInt(9000000));
    }

    private LocalDate generateDateOfBirth() {
        return LocalDate.now().minusYears(18 + random.nextInt(42)); // от 18 до 60 лет
    }

    private BigDecimal generateInitialBalance() {
        return BigDecimal.valueOf(50 + random.nextInt(951)); // от 50 до 1000
    }
}
