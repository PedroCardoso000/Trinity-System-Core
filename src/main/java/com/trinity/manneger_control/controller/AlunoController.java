package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.service.AlunoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoServiceImpl alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.criar(aluno));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id,
            @RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.atualizar(id, aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/quantidade-graus")
    public ResponseEntity<Aluno> updateQuantidadeGraus(@PathVariable Long id,
            @RequestParam Integer quantidadeGraus) {
        return ResponseEntity.ok(alunoService.updateQuantidadeGraus(id, quantidadeGraus));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Aluno> updateFaixa(@PathVariable Long id,
            @RequestParam Faixas faixaEtaria) {
        return ResponseEntity.ok(alunoService.updateFaixa(id, faixaEtaria));
    }

    // Listar por Academia
    @GetMapping("/academia/{academiaId}")
    public ResponseEntity<List<Aluno>> getAlunosByAcademiaId(
            @PathVariable Long academiaId) {
        return ResponseEntity.ok(alunoService.getAlunosByAcademiaId(academiaId));
    }

    // Listar por Branch - Academia
    @GetMapping("/branch/{branchId}/academia/{academiaId}")
    public ResponseEntity<List<Aluno>> getAlunosByBranchIdAndAcademiaId(
            @PathVariable Long branchId,
            @PathVariable Long academiaId) {
        return ResponseEntity.ok(alunoService.getAlunosByBranchIdAndAcademiaId(branchId, academiaId));
    }
}
