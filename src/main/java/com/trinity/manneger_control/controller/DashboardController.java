package com.trinity.manneger_control.controller;
import com.trinity.manneger_control.service.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardServiceImpl dashboardService;

    @GetMapping("/alunos")
    public ResponseEntity<Long> listarTodosAlunosAtivos() {
        return ResponseEntity.ok(dashboardService.listarTodosAlunosAtivos());
    }

    @GetMapping("/filiais")
    public ResponseEntity<Long> listarTodasFiliaisAtivas() {
        return ResponseEntity.ok(dashboardService.listarTodasFiliaisAtivas());
    }
}
