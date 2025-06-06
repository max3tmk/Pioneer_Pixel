package com.max.pioneer_pixel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhoneData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, length = 13)
    private String phone;
}
