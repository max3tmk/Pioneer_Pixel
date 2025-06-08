package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM \"user\" WHERE id = :id", nativeQuery = true)
    Optional<User> getByIdNative(Long id);
}
