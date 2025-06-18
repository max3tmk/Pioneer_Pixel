package com.max.pioneer_pixel.security;

import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String idAsString) throws UsernameNotFoundException {
        Long userId;
        try {
            userId = Long.parseLong(idAsString);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid user ID in token: " + idAsString);
        }

        User user = userDao.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId().toString())
                .password(user.getPassword())
                .authorities("USER") // или роли, если есть
                .build();
    }
}
