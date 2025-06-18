package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryDao extends JpaRepository<TransactionHistory, Long> {
}
