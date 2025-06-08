package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDataDao extends JpaRepository<PhoneData, Long> {
}