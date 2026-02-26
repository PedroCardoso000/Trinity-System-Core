package com.trinity.manneger_control.controller;
import com.trinity.manneger_control.service.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardServiceImpl dashboardService;

    @GetMapping("/{academicId}/active-alunos")
    public ResponseEntity<Long> listarTodosAlunosAtivos(@PathVariable Long academicId) {
        return ResponseEntity.ok(dashboardService.listarTodosAlunosAtivos(academicId));
    }

    @GetMapping("/{academicId}/active-branches")
    public ResponseEntity<Long> listarTodasFiliaisAtivas(@PathVariable Long academicId) {
        return ResponseEntity.ok(dashboardService.listarTodasFiliaisAtivas(academicId));
    }
}
