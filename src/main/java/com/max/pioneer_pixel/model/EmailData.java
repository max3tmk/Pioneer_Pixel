package com.max.pioneer_pixel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true)
    private String email;
}
