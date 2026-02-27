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
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.service.ClassRoomServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final ClassRoomServiceImpl classRoomServiceImpl;

    // Criar aula
    @PostMapping
    public ResponseEntity<ClassRoom> createAula(@RequestBody ClassRoom classRoom) {
        ClassRoom novaAula = classRoomServiceImpl.createClassRoom(classRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAula);
    }

    // Buscar aula por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getAulaById(@PathVariable Long id) {
        return ResponseEntity.ok(classRoomServiceImpl.getClassRoomById(id));
    }

    // Listar todas as aulas
    @GetMapping
    public ResponseEntity<List<ClassRoom>> getAllAulas() {
        return ResponseEntity.ok(classRoomServiceImpl.getAllClasses());
    }

    // Atualizar aula
    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> updateAula(
            @PathVariable Long id,
            @RequestBody ClassRoom classRoom) {

        return ResponseEntity.ok(classRoomServiceImpl.updateClassRoom(id, classRoom));
    }

    // Deletar aula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        classRoomServiceImpl.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }

    // Listar presenças da aula
    @GetMapping("/{id}/attendance")
    public ResponseEntity<List<Attendance>> getAttendanceByClassRoomId(
            @PathVariable Long id) {

        return ResponseEntity.ok(classRoomServiceImpl.getAttendanceByClassRoomId(id));
    }
}
