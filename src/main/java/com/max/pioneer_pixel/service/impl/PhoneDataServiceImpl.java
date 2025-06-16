package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.PhoneDataDao;
import com.max.pioneer_pixel.dao.UserDao;
import com.max.pioneer_pixel.model.PhoneData;
import com.max.pioneer_pixel.model.User;
import com.max.pioneer_pixel.service.PhoneDataService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneDataServiceImpl implements PhoneDataService {

    private final PhoneDataDao phoneDataDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public PhoneData addPhoneData(Long userId, String phoneNumber) {
        Optional<User> userOpt = userDao.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOpt.get();

        PhoneData phoneData = new PhoneData();
        phoneData.setUser(user);
        phoneData.setPhone(phoneNumber);

        return phoneDataDao.save(phoneData);
    }

    @Override
    public List<PhoneData> getPhonesByUserId(Long userId) {
        return phoneDataDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public void deletePhoneData(Long phoneDataId) {
        phoneDataDao.deleteById(phoneDataId);
    }
}
