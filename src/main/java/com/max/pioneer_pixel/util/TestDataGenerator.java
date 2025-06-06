package com.max.pioneer_pixel.util;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.dao.AccountRepository;
import com.max.pioneer_pixel.dao.EmailDataRepository;
import com.max.pioneer_pixel.dao.PhoneDataRepository;
import com.max.pioneer_pixel.dao.UserRepository;
import com.max.pioneer_pixel.model.Account;
import com.max.pioneer_pixel.model.EmailData;
import com.max.pioneer_pixel.model.PhoneData;
import com.max.pioneer_pixel.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TestDataGenerator {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    private final Faker faker = new Faker();

    private static final int USER_COUNT = 10;

    @PostConstruct
    public void generateData() {
        for (int i = 0; i < USER_COUNT; i++) {
            // Создание пользователя
            User user = new User();
            user.setName(faker.name().fullName());
            user.setDateOfBirth(convertToLocalDate(faker.date().birthday()));
            user.setPassword(faker.internet().password(8, 20));
            user = userRepository.save(user);

            // Создание аккаунта
            Account account = new Account();
            account.setUser(user);
            account.setBalance(BigDecimal.valueOf(faker.number().randomDouble(2, 1000, 50000)));
            accountRepository.save(account);

            // Email (1–2)
            int emailCount = faker.number().numberBetween(1, 3);
            for (int j = 0; j < emailCount; j++) {
                EmailData email = new EmailData();
                email.setUser(user);
                email.setEmail(faker.internet().emailAddress());
                emailDataRepository.save(email);
            }

            // Phone (1–2)
            int phoneCount = faker.number().numberBetween(1, 3);
            for (int j = 0; j < phoneCount; j++) {
                PhoneData phone = new PhoneData();
                phone.setUser(user);
                phone.setPhone("7" + faker.number().digits(10));
                phoneDataRepository.save(phone);
            }
        }
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
