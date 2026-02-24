package com.trinity.manneger_control.entity;

import java.time.LocalDateTime;

import com.trinity.manneger_control.domain.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "attendance",
       uniqueConstraints = @UniqueConstraint(
           columnNames = {"alunoId", "aulaId"}
       ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, comment = "ID do aluno")
    private Long alunoId;

    @Column(nullable = false, comment = "ID da aula")
    private Long aulaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, comment = "Status da presença do aluno na aula")
    private AttendanceStatus status;

    private LocalDateTime checkInTime;
}

