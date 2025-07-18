package ru.svanchukov.synthetic_human_core_starter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "android")
@Setter
@Getter
public class Android {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;
}
