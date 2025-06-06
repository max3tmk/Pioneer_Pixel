package com.max.pioneer_pixel.service;

import com.github.javafaker.Faker;
import com.max.pioneer_pixel.model.*;
import com.max.pioneer_pixel.dao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DataGeneratorService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final EmailDataRepository emailDataRepository;
    private final PhoneDataRepository phoneDataRepository;

    private final Faker faker = new Faker(new Locale("ru"));
    private final Random random = new Random();

    public void generateTestData(int count) {
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setDateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            user.setPassword(faker.internet().password(8, 16));
            user = userRepository.save(user);

            Account account = new Account();
            account.setUser(user);
            account.setBalance(BigDecimal.valueOf(random.nextDouble() * 100_000).setScale(2, BigDecimal.ROUND_HALF_UP));
            accountRepository.save(account);

            EmailData emailData = new EmailData();
            emailData.setUser(user);
            emailData.setEmail(faker.internet().emailAddress());
            emailDataRepository.save(emailData);

            PhoneData phoneData = new PhoneData();
            phoneData.setUser(user);
            phoneData.setPhone(generatePhoneNumber());
            phoneDataRepository.save(phoneData);
        }
    }

    private String generatePhoneNumber() {
        return "79" + (100000000 + random.nextInt(900000000));
    }
}
