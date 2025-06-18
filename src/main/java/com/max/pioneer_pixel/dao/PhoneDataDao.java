package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneDataDao extends JpaRepository<PhoneData, Long> {
    Optional<PhoneData> findByPhone(String phone);

    List<PhoneData> findByUserId(Long userId);

    boolean existsByPhone(String phone);

    void deleteAllByUserId(Long userId);
}
