package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trinity.manneger_control.domain.dto.ResponseResult;
import com.trinity.manneger_control.entity.Branch;
import com.trinity.manneger_control.service.BranchServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchServiceImpl branchService;

    @PostMapping
    public ResponseEntity<ResponseResult> criar(@RequestBody Branch branch) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.criar(branch));
    }

    @GetMapping
    public ResponseEntity<List<Branch>> listarTodos() {
        return ResponseEntity.ok(branchService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.buscarPorId(id));
    }

    @GetMapping("/academic/{idAcademia}")
    public ResponseEntity<List<Branch>> buscarPorIdAcademia(@PathVariable Long idAcademia) {
        return ResponseEntity.ok(branchService.buscarPorIdAcademia(idAcademia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> atualizar(@PathVariable Long id, @RequestBody Branch branchAtualizado) {
        return ResponseEntity.ok(branchService.atualizar(id, branchAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        branchService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
