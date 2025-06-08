package com.max.pioneer_pixel.service;

import com.max.pioneer_pixel.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createInitialAccount(Long userId, BigDecimal initialBalance);
}
