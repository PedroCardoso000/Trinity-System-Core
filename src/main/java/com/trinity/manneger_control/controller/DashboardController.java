package com.trinity.manneger_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.manneger_control.domain.dto.DashboardMetricResponse;
import com.trinity.manneger_control.service.DashboardServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardServiceImpl dashboardService;

   @GetMapping("/{academicId}/active-alunos")
    public ResponseEntity<DashboardMetricResponse> countAllActiveStudents(@PathVariable Long academicId) {
        long value = dashboardService.countAllActiveStudents(academicId);
        return ResponseEntity.ok(new DashboardMetricResponse("active-alunos", value));
    }

    @GetMapping("/{academicId}/monthly-birthdays")
    public ResponseEntity<DashboardMetricResponse> countAllMonthlyBirthdays(@PathVariable Long academicId) {
        long value = dashboardService.countAllMonthlyBirthdays(academicId);
        return ResponseEntity.ok(new DashboardMetricResponse("monthly-birthdays", value));
    }

    @GetMapping("/{academicId}/classes-today")
    public ResponseEntity<DashboardMetricResponse> countAllLessonsToday(@PathVariable Long academicId) {
        long value = dashboardService.countAllLessonsToday(academicId);
        return ResponseEntity.ok(new DashboardMetricResponse("classes-today", value));
    }

    @GetMapping("/{academicId}/monthly-attendances")
    public ResponseEntity<DashboardMetricResponse> countAllMonthlyPresences(@PathVariable Long academicId) {
        long value = dashboardService.countAllMonthlyPresences(academicId);
        return ResponseEntity.ok(new DashboardMetricResponse("monthly-attendances", value));
    }

    @GetMapping("/{academicId}/active-branches")
    public ResponseEntity<DashboardMetricResponse> countAllActiveBranchess(@PathVariable Long academicId) {
        long value = dashboardService.countAllActiveBranchess(academicId);
        return ResponseEntity.ok(new DashboardMetricResponse("active-branches", value));
    }
}