package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}
