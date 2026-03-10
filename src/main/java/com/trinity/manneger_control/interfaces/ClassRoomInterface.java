package com.trinity.manneger_control.interfaces;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;

public interface ClassRoomInterface {
    ClassRoom createClassRoom(ClassRoom classEntity);

    ClassRoom getClassRoomById(Long id);

    List<ClassRoom> getAllClasses();

    ClassRoom updateClassRoom(Long id, ClassRoom classEntity);

    void deleteClassRoom(Long id);

    List<Attendance> getAttendanceByClassRoomId(Long classRoomId);

    List<ClassRoom> findFiltered(Long branchId, Long academicId, DayOfWeek dayOfWeek, LocalDate date);

}

