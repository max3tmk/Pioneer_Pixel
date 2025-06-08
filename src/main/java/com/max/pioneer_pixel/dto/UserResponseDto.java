package com.max.pioneer_pixel.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}
