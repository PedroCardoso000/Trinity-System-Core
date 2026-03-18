package com.trinity.manneger_control.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "class_room",
       uniqueConstraints = @UniqueConstraint(columnNames = {"scheduleId", "dateTime"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private LocalDateTime dateTime; // 2026-03-06T19:00

    @Column(nullable = false)
    private Boolean cancelled;

    @Column(nullable = false)
    private Long branchId;

    @Column(nullable = false)
    private Long academicId;
}
