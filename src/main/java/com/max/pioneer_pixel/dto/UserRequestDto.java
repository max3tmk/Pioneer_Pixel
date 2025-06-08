package com.max.pioneer_pixel.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal initialBalance;
}
