package com.trinity.manneger_control.service;

import com.trinity.manneger_control.domain.dto.ResponseResult;
import com.trinity.manneger_control.entity.ClassSchedule;
import com.trinity.manneger_control.repository.ClassRoomRepository;
import com.trinity.manneger_control.repository.ClassScheduleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassScheduleServiceImpl {

    private final ClassScheduleRepository classScheduleRepository;

    public ResponseEntity<ResponseResult> create(ClassSchedule schedule) {
        ClassSchedule savedSchedule = classScheduleRepository.save(schedule);
        if (savedSchedule == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseResult(false, "Error to create schedule"));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseResult(true, "Schedule created successfully"));
    }

    public ClassSchedule getById(Long id) {
        return classScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public List<ClassSchedule> getAll() {
        return classScheduleRepository.findAll();
    }

    public List<ClassSchedule> getByBranchAndAcademic(Long branchId, Long academicId) {
        return classScheduleRepository.findByBranchIdAndAcademicId(branchId, academicId);
    }

    public ResponseEntity<ResponseResult> update(Long id, ClassSchedule updated) {
        if (updated.getDayOfWeek() == null || updated.getTime() == null || updated.getStartDate() == null
                || updated.getActive() == null || updated.getBranchId() == null
                || updated.getAcademicId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseResult(false, "Error to update schedule"));
        }
        ClassSchedule existing = getById(id);

        existing.setName(updated.getName());
        existing.setDayOfWeek(updated.getDayOfWeek());
        existing.setTime(updated.getTime());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setActive(updated.getActive());
        existing.setBranchId(updated.getBranchId());
        existing.setAcademicId(updated.getAcademicId());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseResult(true, "Schedule updated successfully"));
    }

    public ResponseEntity<ResponseResult> delete(Long id) {
        ClassSchedule existing = getById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseResult(false, "Error to delete schedule"));
        }
        classScheduleRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseResult(true, "Schedule deleted successfully"));
    }
}
