package com.max.pioneer_pixel.service;

import com.max.pioneer_pixel.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccountForUser(Long userId, BigDecimal initialBalance);

    Account getAccountByUserId(Long userId);

    void updateBalance(Long userId, BigDecimal newBalance);

    boolean transfer(Long fromUserId, Long toUserId, BigDecimal amount);
}
