package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom {
    Optional<User> findByName(String name);
}
