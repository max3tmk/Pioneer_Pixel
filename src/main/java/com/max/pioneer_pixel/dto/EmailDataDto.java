package com.max.pioneer_pixel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmailDataDto {
    @Email
    @NotBlank
    private String email;

    private boolean verified;
}
