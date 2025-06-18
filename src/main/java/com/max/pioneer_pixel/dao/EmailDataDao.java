package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailDataDao extends JpaRepository<EmailData, Long> {
    Optional<EmailData> findByEmail(String email);

    List<EmailData> findByUserId(Long userId);

    boolean existsByEmail(String email);

    void deleteAllByUserId(Long userId);
}
