package com.max.pioneer_pixel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank
    private String fullName;

    @NotNull
    private String gender;

    @NotNull
    private Integer age;

    @NotNull
    private AccountDto account;

    @NotNull
    private EmailDataDto emailData;

    @NotNull
    private PhoneDataDto phoneData;
}
