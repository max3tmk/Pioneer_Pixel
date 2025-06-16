package com.max.pioneer_pixel.dao;

import com.max.pioneer_pixel.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.phoneDataList p WHERE p.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);

    @Query("SELECT u FROM User u JOIN u.emailDataList e WHERE e.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
