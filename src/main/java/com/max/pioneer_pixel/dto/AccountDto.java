package com.max.pioneer_pixel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AccountDto {
    @NotBlank
    private String accountName;

    @NotNull
    @PositiveOrZero
    private Double balance;
}
