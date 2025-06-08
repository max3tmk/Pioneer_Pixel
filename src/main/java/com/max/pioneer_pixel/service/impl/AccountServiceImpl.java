package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.AccountDao;
import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.model.Account;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public Account createInitialAccount(Long userId, BigDecimal initialBalance) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Account account = new Account();
        account.setUser(user);
        account.setBalance(initialBalance);
        account.setAccountName(user.getName() + "'s account"); // ✅ фикс: имя аккаунта обязательно

        return accountDao.save(account);
    }
}
