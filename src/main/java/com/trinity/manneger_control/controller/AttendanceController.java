package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.manneger_control.domain.dto.AttendanceResponse;
import com.trinity.manneger_control.domain.dto.CheckInRequest;
import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.service.AttendanceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServiceImpl attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponse> checkIn(
            @RequestBody CheckInRequest request) {

        AttendanceResponse response =
                attendanceService.checkIn(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/absent")
    public ResponseEntity<Void> marcarFalta(
            @RequestParam Long alunoId,
            @RequestParam Long classRoomId) {

        attendanceService.marcarFalta(alunoId, classRoomId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/class-room/{classRoomId}")
    public ResponseEntity<List<Attendance>> listarPorAula(
            @PathVariable Long classRoomId) {

        return ResponseEntity.ok(
                attendanceService.listarPorAula(classRoomId)
        );
    }
}

