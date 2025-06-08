package com.max.pioneer_pixel.service.impl;

import com.max.pioneer_pixel.dao.EmailDataDao;
import com.max.pioneer_pixel.model.EmailData;
import com.max.pioneer_pixel.service.EmailDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailDataServiceImpl implements EmailDataService {

    private final EmailDataDao emailDataDao;

    @Override
    public EmailData createEmail(String email) {
        EmailData emailData = new EmailData();
        emailData.setEmail(email);
        return emailData;
    }
}
