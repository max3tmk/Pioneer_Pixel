package com.max.pioneer_pixel;

import com.max.pioneer_pixel.service.DataGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DataGeneratorService dataGeneratorService;

    @Override
    public void run(String... args) {
        dataGeneratorService.generateTestData(20);
    }
}
