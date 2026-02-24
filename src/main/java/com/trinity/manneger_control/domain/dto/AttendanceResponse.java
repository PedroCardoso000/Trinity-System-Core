package com.trinity.manneger_control.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AttendanceResponse {
    private Long id;
    private Long alunoId;
    private Long aulaId;
    private String status;
    private LocalDateTime checkInTime;
}
