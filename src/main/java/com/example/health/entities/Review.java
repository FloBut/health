package com.example.health.entities;

import jakarta.persistence.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
