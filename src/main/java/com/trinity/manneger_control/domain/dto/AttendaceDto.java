package com.trinity.manneger_control.domain.dto;

import java.time.LocalDateTime;

import com.trinity.manneger_control.domain.AttendanceStatus;

public record AttendaceDto(
        Long id,
        String student,
        Long classRoomId,
        AttendanceStatus status,
        LocalDateTime checkInTime
) {
}