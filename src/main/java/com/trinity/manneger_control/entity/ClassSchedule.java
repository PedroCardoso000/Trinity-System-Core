package com.trinity.manneger_control.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, comment = "Day of week schedule")
    private DayOfWeek dayOfWeek; // FRIDAY

    @Column(nullable = false, comment = "Time schedule")
    private LocalTime time; // 19:00

    @Column(nullable = false, comment = "Start date schedule")
    private LocalDate startDate; // 2026-01-01

    private LocalDate endDate;

    private LocalDate generatedUntil;

    @Column(nullable = false, comment = "Active schedule")
    private Boolean active;

    @Column(nullable = false, comment = "Id branch null")
    private Long branchId;

    @Column(nullable = false, comment = "Id academic null")
    private Long academicId;
}
