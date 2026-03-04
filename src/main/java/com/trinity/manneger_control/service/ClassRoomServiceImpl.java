package com.trinity.manneger_control.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl  {

    private final ClassRoomRepository classRoomRepository;
    private final AttendanceRepository attendanceRepository;

    public ClassRoom create(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public ClassRoom getById(Long id) {
        return classRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }

    public ClassRoom update(Long id, ClassRoom updated) {

        ClassRoom existing = getById(id);

        existing.setDateTime(updated.getDateTime());
        existing.setCancelled(updated.getCancelled());
        existing.setBranchId(updated.getBranchId());
        existing.setScheduleId(updated.getScheduleId());
        existing.setAcademicId(updated.getAcademicId());

        return classRoomRepository.save(existing);
    }

    public void delete(Long id) {
        classRoomRepository.deleteById(id);
    }

    public List<Attendance> getAttendance(Long classRoomId) {
        return attendanceRepository.findByClassRoomId(classRoomId);
    }
}

