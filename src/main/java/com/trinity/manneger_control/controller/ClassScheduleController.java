package com.trinity.manneger_control.controller;

import com.trinity.manneger_control.entity.ClassSchedule;
import com.trinity.manneger_control.service.ClassScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-schedules")
@RequiredArgsConstructor
public class ClassScheduleController {

    private final ClassScheduleServiceImpl classScheduleService;

    @PostMapping
    public ResponseEntity<ClassSchedule> create(
            @RequestBody ClassSchedule schedule) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(classScheduleService.create(schedule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedule> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(classScheduleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassSchedule>> getAll() {
        return ResponseEntity.ok(
                classScheduleService.getAll());
    }

    @GetMapping("/filter/academic-branch")
    public ResponseEntity<List<ClassSchedule>> getByBranch(
            @RequestParam Long branchId,
            @RequestParam Long academicId) {

        return ResponseEntity.ok(
                classScheduleService.getByBranchAndAcademic(branchId, academicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassSchedule> update(
            @PathVariable Long id,
            @RequestBody ClassSchedule schedule) {

        return ResponseEntity.ok(
                classScheduleService.update(id, schedule));
    }

    // POST /class-schedules/{id}/generate-next
    @PostMapping("/{id}/generate-next")
    public ResponseEntity<Void> generateNext(
            @PathVariable Long id) {

        classScheduleService.generateNext(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        classScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
