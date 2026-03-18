package com.trinity.manneger_control.controller;

import com.trinity.manneger_control.domain.dto.ResponseResult;
import com.trinity.manneger_control.entity.ClassSchedule;
import com.trinity.manneger_control.service.ClassScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-schedules")
@RequiredArgsConstructor
public class ClassScheduleController {

    private final ClassScheduleServiceImpl classScheduleService;

    @PostMapping
    public ResponseEntity<ResponseResult> create(@RequestBody ClassSchedule schedule) {
        return classScheduleService.create(schedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(classScheduleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassSchedule>> getAll() {
        return ResponseEntity.ok(classScheduleService.getAll());
    }

    @GetMapping("/filter/academic-branch")
    public ResponseEntity<List<ClassSchedule>> getByBranch(
            @RequestParam Long branchId,
            @RequestParam Long academicId) {

        return ResponseEntity.ok(
                classScheduleService.getByBranchAndAcademic(branchId, academicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseResult> update(
            @PathVariable Long id,
            @RequestBody ClassSchedule schedule) {

        return classScheduleService.update(id, schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult> delete(@PathVariable Long id) {
        return classScheduleService.delete(id);
    }
}
