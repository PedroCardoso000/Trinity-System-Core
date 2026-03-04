package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceServiceImpl attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<AttendanceResponse> checkIn(
            @RequestParam Long alunoId,
            @RequestParam Long classRoomId) {

        return ResponseEntity.ok(
                attendanceService.checkIn(alunoId, classRoomId));
    }

    @PostMapping("/pendant")
    public ResponseEntity<Void> markPendant(
            @RequestBody CheckInRequest request) {

        attendanceService.marcarPendente(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/absent")
    public ResponseEntity<Void> markAbsent(
            @RequestParam Long alunoId,
            @RequestParam Long classRoomId) {

        attendanceService.marcarFalta(alunoId, classRoomId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/classroom/{classRoomId}")
    public ResponseEntity<List<Attendance>> getByClassRoom(
            @PathVariable Long classRoomId) {

        return ResponseEntity.ok(
                attendanceService.listarPorAula(classRoomId));
    }
}
