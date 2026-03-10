package com.trinity.manneger_control.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.interfaces.ClassRoomInterface;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomInterface {

    private final ClassRoomRepository classRoomRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public ClassRoom createClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public ClassRoom getClassRoomById(Long id) {
        return classRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    @Override
    public List<ClassRoom> getAllClasses() {
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom updateClassRoom(Long id, ClassRoom updated) {

        ClassRoom existing = getClassRoomById(id);

        existing.setDateTime(updated.getDateTime());
        existing.setCancelled(updated.getCancelled());
        existing.setBranchId(updated.getBranchId());
        existing.setScheduleId(updated.getScheduleId());
        existing.setAcademicId(updated.getAcademicId());

        return classRoomRepository.save(existing);
    }

    @Override
    public void deleteClassRoom(Long id) {
        classRoomRepository.deleteById(id);
    }

    @Override
    public List<Attendance> getAttendanceByClassRoomId(Long classRoomId) {
        return attendanceRepository.findByClassRoomId(classRoomId);
    }

    @Override
    public List<ClassRoom> findFiltered(
            Long branchId,
            Long academicId,
            DayOfWeek dayOfWeek,
            LocalDate date) {

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null) {
            start = date.atStartOfDay();
            end = date.plusDays(1).atStartOfDay();
        }

        List<ClassRoom> classRooms;

        if (start != null && end != null) {

            classRooms = classRoomRepository
                    .findByBranchIdAndAcademicIdAndDateTimeBetween(
                            branchId,
                            academicId,
                            start,
                            end);

        } else {

            classRooms = classRoomRepository
                    .findByBranchIdAndAcademicId(
                            branchId,
                            academicId);
        }

        if (dayOfWeek != null) {

            classRooms = classRooms.stream()
                    .filter(cr -> cr.getDateTime()
                            .getDayOfWeek()
                            .equals(dayOfWeek))
                    .toList();
        }

        return classRooms;
    }
}
