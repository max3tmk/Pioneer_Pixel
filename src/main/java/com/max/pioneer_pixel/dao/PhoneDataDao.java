package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneDataDao extends JpaRepository<PhoneData, Long> {

    List<PhoneData> findByUserId(Long userId);
}
