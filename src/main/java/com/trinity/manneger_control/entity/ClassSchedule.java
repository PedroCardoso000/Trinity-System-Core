package com.trinity.manneger_control.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "class_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek; // FRIDAY

    @Column(nullable = false)
    private LocalTime time; // 19:00

    @Column(nullable = false)
    private LocalDate startDate; // 2026-01-01

    private LocalDate endDate;

    private LocalDate generatedUntil;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Long branchId;

    @Column(nullable = false)
    private Long academicId;
}
