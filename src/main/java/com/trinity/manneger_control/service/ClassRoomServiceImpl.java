package com.trinity.manneger_control.service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.domain.dto.ResponseResult;
import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.entity.ClassSchedule;
import com.trinity.manneger_control.interfaces.ClassRoomInterface;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;
import com.trinity.manneger_control.repository.ClassScheduleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomInterface {

    private final ClassRoomRepository classRoomRepository;
    private final AttendanceRepository attendanceRepository;
    private final ClassScheduleRepository classScheduleRepository;

    private record Key(Long scheduleId, LocalDateTime dateTime) {
    }

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

    public ResponseEntity<ResponseResult> classRoomCreateOrVerify(Long scheduleId, Long alunoId, LocalDateTime dateTime,
            Long branchId, Long academicId) {

        ClassRoom classRoom = getOrCreateRealClassRoom(
                scheduleId, dateTime, branchId, academicId);

        if (classRoom.getCancelled()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseResult(false, "Class cancelled"));
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseResult(true, "Class checked in successfully"));
    }

    public ResponseEntity<ResponseResult> cancelarAula(Long scheduleId, LocalDateTime dateTime, Long branchId,
            Long academicId) {
        ClassRoom classRoom = getOrCreateRealClassRoom(scheduleId, dateTime, branchId, academicId);
        classRoom.setCancelled(true);
        classRoomRepository.save(classRoom);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseResult(true, "Class cancelled successfully"));
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

        List<ClassSchedule> schedules = classScheduleRepository
                .findByBranchIdAndAcademicId(branchId, academicId)
                .stream()
                .filter(ClassSchedule::getActive)
                .collect(Collectors.toList());

        LocalDate startDate = (date != null) ? date : LocalDate.now();
        LocalDate endDate = startDate;

        List<ClassRoom> virtualClassRooms = new ArrayList<>();

        for (ClassSchedule schedule : schedules) {

            if (dayOfWeek != null && schedule.getDayOfWeek() != dayOfWeek) {
                continue;
            }

            LocalDate current = startDate;

            while (!current.isAfter(endDate)) {

                if (current.getDayOfWeek() == schedule.getDayOfWeek() &&
                        !current.isBefore(schedule.getStartDate()) &&
                        (schedule.getEndDate() == null || !current.isAfter(schedule.getEndDate()))) {

                    ClassRoom virtual = new ClassRoom();
                    virtual.setScheduleId(schedule.getId());
                    virtual.setDateTime(current.atTime(schedule.getTime()));
                    virtual.setBranchId(branchId);
                    virtual.setAcademicId(academicId);
                    virtual.setCancelled(false);

                    virtualClassRooms.add(virtual);
                }

                current = current.plusDays(1);
            }
        }

        List<ClassRoom> realClassRooms = classRoomRepository
                .findByBranchIdAndAcademicIdAndDateTimeBetween(
                        branchId,
                        academicId,
                        startDate.atStartOfDay(),
                        endDate.atTime(23, 59, 59));

        Map<Key, ClassRoom> realMap = realClassRooms.stream()
                .collect(Collectors.toMap(
                        cr -> new Key(cr.getScheduleId(), cr.getDateTime()),
                        cr -> cr,
                        (existing, replacement) -> existing));

        List<ClassRoom> result = new ArrayList<>();

        for (ClassRoom virtual : virtualClassRooms) {
            Key key = new Key(virtual.getScheduleId(), virtual.getDateTime());

            if (realMap.containsKey(key)) {
                result.add(realMap.get(key));
            } else {
                result.add(virtual);
            }
        }

        return result;
    }

    public ClassRoom getOrCreateRealClassRoom(Long scheduleId, LocalDateTime dateTime,
            Long branchId, Long academicId) {

        return classRoomRepository.findByScheduleIdAndDateTime(scheduleId, dateTime)
                .orElseGet(() -> {
                    ClassRoom newCr = new ClassRoom();
                    newCr.setScheduleId(scheduleId);
                    newCr.setDateTime(dateTime);
                    newCr.setBranchId(branchId);
                    newCr.setAcademicId(academicId);
                    newCr.setCancelled(false);
                    return classRoomRepository.save(newCr);
                });
    }
}