package com.trinity.manneger_control.utils;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.trinity.manneger_control.entity.ClassSchedule;

import com.trinity.manneger_control.repository.ClassScheduleRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClassScheduleVerify {

    private final ClassScheduleRepository classScheduleRepository;

    public void verifiyClassSchedule(Long scheduleId, LocalDateTime dateTime, Long branchId, Long academicId) {

        Optional<ClassSchedule> schedule = classScheduleRepository.findById(scheduleId);
        
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("Schedule not found");
        }
        if (schedule.isPresent() && !schedule.get().getActive()) {
            throw new IllegalArgumentException("Schedule not active");
        }
        if (schedule.isPresent() && schedule.get().getDayOfWeek() != dateTime.getDayOfWeek()) {
            throw new IllegalArgumentException("Day of week not found");
        }
        if (schedule.isPresent() && schedule.get().getAcademicId() != academicId) {
            throw new IllegalArgumentException("Academic not found");
        }
        if (schedule.isPresent() && schedule.get().getBranchId() != branchId) {
            throw new IllegalArgumentException("Branch not found");
        }
    }
}
