package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.Aula;
import com.trinity.manneger_control.service.AulaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaServiceImpl aulaServiceImpl;

    // Criar aula
    @PostMapping
    public ResponseEntity<Aula> createAula(@RequestBody Aula aula) {
        Aula novaAula = aulaServiceImpl.createAula(aula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAula);
    }

    // Buscar aula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Long id) {
        return ResponseEntity.ok(aulaServiceImpl.getAulaById(id));
    }

    // Listar todas as aulas
    @GetMapping
    public ResponseEntity<List<Aula>> getAllAulas() {
        return ResponseEntity.ok(aulaServiceImpl.getAllAulas());
    }

    // Atualizar aula
    @PutMapping("/{id}")
    public ResponseEntity<Aula> updateAula(
            @PathVariable Long id,
            @RequestBody Aula aula) {

        return ResponseEntity.ok(aulaServiceImpl.updateAula(id, aula));
    }

    // Deletar aula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        aulaServiceImpl.deleteAula(id);
        return ResponseEntity.noContent().build();
    }

    // Listar presenças da aula
    @GetMapping("/{id}/attendance")
    public ResponseEntity<List<Attendance>> getAttendanceByAulaId(
            @PathVariable Long id) {

        return ResponseEntity.ok(aulaServiceImpl.getAttendanceByAulaId(id));
    }
}
