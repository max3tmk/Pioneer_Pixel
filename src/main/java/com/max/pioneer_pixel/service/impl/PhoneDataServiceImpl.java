package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.PhoneDataDao;
import com.max.pioneer_pixel.model.PhoneData;
import com.max.pioneer_pixel.service.PhoneDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneDataServiceImpl implements PhoneDataService {

    private final PhoneDataDao phoneDataDao;

    @Override
    public PhoneData createPhone(String phoneNumber) {
        PhoneData phoneData = new PhoneData();
        phoneData.setPhoneNumber(phoneNumber);
        return phoneData;
    }
}
