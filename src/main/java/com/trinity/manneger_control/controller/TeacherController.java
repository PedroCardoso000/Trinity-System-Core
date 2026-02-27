package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trinity.manneger_control.entity.Teacher;
import com.trinity.manneger_control.service.TeacherServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @PostMapping
    public ResponseEntity<Teacher> criar(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.criar(teacher));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> listarTodos() {
        return ResponseEntity.ok(teacherService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> atualizar(@PathVariable Long id,
            @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.atualizar(id, teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        teacherService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/quantidade-graus")
    public ResponseEntity<Teacher> updateQuantidadeGraus(@PathVariable Long id,
            @RequestParam Integer quantidadeGraus) {
        return ResponseEntity.ok(teacherService.updateQuantidadeGraus(id, quantidadeGraus));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Teacher> updateActive(@PathVariable Long id,
            @RequestParam Boolean active) {
        return ResponseEntity.ok(teacherService.updateActive(id, active));
    }

    // Listar por Academia
    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<Teacher>> getTeachersByAcademiaId(
            @PathVariable Long academiaId) {
        return ResponseEntity.ok(teacherService.getTeachersByAcademiaId(academiaId));
    }

    // Listar por Branch - Academia
    @GetMapping("/branch/{branchId}/academia/{academiaId}")
    public ResponseEntity<List<Teacher>> getTeachersByBranchIdAndAcademiaId(
            @PathVariable Long branchId,
            @PathVariable Long academiaId) {
        return ResponseEntity.ok(teacherService.getTeachersByBranchIdAndAcademiaId(branchId, academiaId));
    }
}
