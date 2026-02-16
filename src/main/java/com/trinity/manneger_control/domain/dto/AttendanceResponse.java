package com.trinity.manneger_control.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Builder;

@Builder
@Getter
public class AttendanceResponse {
    private Long id;
    private Long alunoId;
    private Long aulaId;
    private String status;
    private LocalDateTime checkInTime;
}
