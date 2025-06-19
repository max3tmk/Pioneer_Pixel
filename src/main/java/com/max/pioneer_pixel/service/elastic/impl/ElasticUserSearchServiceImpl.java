package com.max.pioneer_pixel.service.elastic.impl;

import com.max.pioneer_pixel.dao.EmailDataDao;
import com.max.pioneer_pixel.dao.PhoneDataDao;
import com.max.pioneer_pixel.dao.elastic.ElasticUserRepository;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.model.elastic.ElasticUser;
import com.max.pioneer_pixel.model.EmailData;
import com.max.pioneer_pixel.model.PhoneData;
import com.max.pioneer_pixel.service.elastic.ElasticUserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ElasticUserSearchServiceImpl implements ElasticUserSearchService {

    private final ElasticUserRepository elasticUserRepository;
    private final EmailDataDao emailDataDao;
    private final PhoneDataDao phoneDataDao;

    @Override
    public void indexUser(User user) {
        ElasticUser eu = new ElasticUser();
        eu.setId(user.getId());
        eu.setName(user.getName());
        eu.setDateOfBirth(user.getDateOfBirth());

        Optional<EmailData> emailOpt = emailDataDao.findFirstByUserId(user.getId());
        Optional<PhoneData> phoneOpt = phoneDataDao.findFirstByUserId(user.getId());

        emailOpt.ifPresent(email -> eu.setEmail(email.getEmail()));
        phoneOpt.ifPresent(phone -> eu.setPhone(phone.getPhone()));

        elasticUserRepository.save(eu);
    }

    @Override
    public Page<ElasticUser> search(String name, String email, String phone, LocalDate dateOfBirth, Pageable pageable) {
        List<ElasticUser> filtered = StreamSupport
                .stream(elasticUserRepository.findAll().spliterator(), false)
                .filter(user -> name == null || user.getName().toLowerCase().startsWith(name.toLowerCase()))
                .filter(user -> email == null || email.equalsIgnoreCase(user.getEmail()))
                .filter(user -> phone == null || phone.equals(user.getPhone()))
                .filter(user -> dateOfBirth == null || user.getDateOfBirth().isAfter(dateOfBirth))
                .collect(Collectors.toList());

        int total = filtered.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), total);
        List<ElasticUser> pageContent = filtered.subList(Math.min(start, total), Math.min(end, total));

        return new PageImpl<>(pageContent, pageable, total);
    }

    @Override
    public Page<User> searchUsersViaElastic(String name, String email, String phone, LocalDate dateOfBirth, Pageable pageable) {
        Page<ElasticUser> elasticPage = search(name, email, phone, dateOfBirth, pageable);
        List<User> users = elasticPage.getContent().stream()
                .map(this::toUser)
                .collect(Collectors.toList());
        return new PageImpl<>(users, pageable, elasticPage.getTotalElements());
    }

    private User toUser(ElasticUser eu) {
        User u = new User();
        u.setId(eu.getId());
        u.setName(eu.getName());
        u.setDateOfBirth(eu.getDateOfBirth());
        // Упрощённо — email/phone находятся только в ElasticUser, не в User
        return u;
    }
}
