package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.service.ClassRoomServiceImpl;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomServiceImpl classRoomService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                classRoomService.getClassRoomById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassRoom>> getAll() {

        return ResponseEntity.ok(
                classRoomService.getAllClasses());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ClassRoom>> getFiltered(
            @RequestParam Long branchId,
            @RequestParam Long academicId,
            @RequestParam(required = false) DayOfWeek dayOfWeek,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                classRoomService.findFiltered(branchId, academicId, dayOfWeek, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> update(
            @PathVariable Long id,
            @RequestBody ClassRoom classRoom) {

        return ResponseEntity.ok(
                classRoomService.updateClassRoom(id, classRoom));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable Long id) {

        ClassRoom classRoom = classRoomService.getClassRoomById(id);
        classRoom.setCancelled(true);
        classRoomService.updateClassRoom(id, classRoom);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        classRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }
}
