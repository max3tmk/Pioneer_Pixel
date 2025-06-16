package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.AccountDao;
import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.model.Account;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public Account createAccountForUser(Long userId, BigDecimal initialBalance) {
        Optional<User> userOpt = userDao.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOpt.get();

        Account account = new Account();
        account.setUser(user);
        account.setBalance(initialBalance);
        account.setInitialBalance(initialBalance);

        return accountDao.save(account);
    }

    @Override
    public Account getAccountByUserId(Long userId) {
        return accountDao.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    @Override
    @Transactional
    public void updateBalance(Long userId, BigDecimal newBalance) {
        Account account = getAccountByUserId(userId);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        account.setBalance(newBalance);
        accountDao.save(account);
    }

    @Override
    @Transactional
    public boolean transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Cannot transfer to the same user");
        }

        Account fromAccount = getAccountByUserId(fromUserId);
        Account toAccount = getAccountByUserId(toUserId);

        synchronized (this) {
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            BigDecimal newFromBalance = fromAccount.getBalance().subtract(amount);
            BigDecimal newToBalance = toAccount.getBalance().add(amount);

            fromAccount.setBalance(newFromBalance);
            toAccount.setBalance(newToBalance);

            accountDao.save(fromAccount);
            accountDao.save(toAccount);
        }

        return true;
    }
}
