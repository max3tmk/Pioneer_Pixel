package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataDao extends JpaRepository<EmailData, Long> {
}
