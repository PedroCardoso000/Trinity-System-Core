package com.trinity.manneger_control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.service.ClassRoomServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomServiceImpl classRoomService;

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                classRoomService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassRoom>> getAll() {

        return ResponseEntity.ok(
                classRoomService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> update(
            @PathVariable Long id,
            @RequestBody ClassRoom classRoom) {

        return ResponseEntity.ok(
                classRoomService.update(id, classRoom));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable Long id) {

        ClassRoom classRoom = classRoomService.getById(id);
        classRoom.setCancelled(true);
        classRoomService.update(id, classRoom);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        classRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
