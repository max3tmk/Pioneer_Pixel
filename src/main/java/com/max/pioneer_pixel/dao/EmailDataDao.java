package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailDataDao extends JpaRepository<EmailData, Long> {

    List<EmailData> findByUserId(Long userId);
}
