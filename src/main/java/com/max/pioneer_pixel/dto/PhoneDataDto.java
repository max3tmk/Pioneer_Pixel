package com.max.pioneer_pixel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PhoneDataDto {
    @NotBlank
    private String phoneNumber;

    private boolean verified;
}
