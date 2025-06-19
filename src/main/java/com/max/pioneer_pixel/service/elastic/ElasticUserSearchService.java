package com.max.pioneer_pixel.service.elastic;

import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.model.elastic.ElasticUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ElasticUserSearchService {

    void indexUser(User user);

    Page<ElasticUser> search(String name, String email, String phone, LocalDate dateOfBirth, Pageable pageable);

    Page<User> searchUsersViaElastic(String name, String email, String phone, LocalDate dateOfBirth, Pageable pageable);
}
