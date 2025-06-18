package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.EmailDataDao;
import com.max.pioneer_pixel.dao.PhoneDataDao;
import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.model.EmailData;
import com.max.pioneer_pixel.model.PhoneData;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.AccountService;
import com.max.pioneer_pixel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final EmailDataDao emailDataDao;
    private final PhoneDataDao phoneDataDao;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder; // Добавили PasswordEncoder

    @Override
    public User createUserWithAccount(User user, BigDecimal initialBalance) {
        // Хэшируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userDao.save(user);
        accountService.createInitialAccount(savedUser, initialBalance);
        return savedUser;
    }

    @Override
    public Page<User> searchUsers(String name, String email, String phone, LocalDate dateOfBirth, Pageable pageable) {
        try {
            return userDao.searchUsers(name, email, phone, dateOfBirth, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    @Transactional
    public void addEmail(Long userId, String email) {
        if (emailDataDao.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already taken by another user");
        }
        EmailData newEmail = new EmailData();
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        newEmail.setUser(user);
        newEmail.setEmail(email);
        emailDataDao.save(newEmail);
    }

    @Override
    @Transactional
    public void deleteEmail(Long userId, String email) {
        emailDataDao.findByEmail(email)
                .filter(e -> e.getUser().getId().equals(userId))
                .ifPresent(emailDataDao::delete);
    }

    @Override
    @Transactional
    public void addPhone(Long userId, String phone) {
        if (phoneDataDao.findByPhone(phone).isPresent()) {
            throw new IllegalArgumentException("Phone is already taken by another user");
        }
        PhoneData newPhone = new PhoneData();
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
        newPhone.setUser(user);
        newPhone.setPhone(phone);
        phoneDataDao.save(newPhone);
    }

    @Override
    @Transactional
    public void deletePhone(Long userId, String phone) {
        phoneDataDao.findByPhone(phone)
                .filter(e -> e.getUser().getId().equals(userId))
                .ifPresent(phoneDataDao::delete);
    }

    @Override
    public List<String> getEmails(Long userId) {
        return emailDataDao.findByUserId(userId)
                .stream()
                .map(EmailData::getEmail)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPhones(Long userId) {
        return phoneDataDao.findByUserId(userId)
                .stream()
                .map(PhoneData::getPhone)
                .collect(Collectors.toList());
    }
}
