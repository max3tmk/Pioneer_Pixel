package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
