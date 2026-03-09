package com.trinity.manneger_control.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.ClassRoom;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

        List<ClassRoom> findByBranchId(Long branchId);

        List<ClassRoom> findByScheduleId(Long scheduleId);

        List<ClassRoom> findByBranchIdAndAcademicId(Long branchId, Long academicId);

        boolean existsByScheduleIdAndDateTime(Long scheduleId, LocalDateTime dateTime);

        List<ClassRoom> findByBranchIdAndDateTimeBetween(
                        Long branchId,
                        LocalDateTime start,
                        LocalDateTime end);

        // @Query("""
        // SELECT cr FROM ClassRoom cr
        // JOIN ClassSchedule cs ON cr.scheduleId = cs.id
        // WHERE cr.branchId = :branchId
        // AND cr.academicId = :academicId
        // AND (:dayOfWeek IS NULL OR cs.dayOfWeek = CAST(:dayOfWeek AS string))
        // AND (:start IS NULL OR cr.dateTime >= :start)
        // AND (:end IS NULL OR cr.dateTime <= :end)
        // """)
        // List<ClassRoom> findFiltered(
        // @Param("branchId") Long branchId,
        // @Param("academicId") Long academicId,
        // @Param("dayOfWeek") DayOfWeek dayOfWeek,
        // @Param("start") LocalDateTime start,
        // @Param("end") LocalDateTime end);

        List<ClassRoom> findByBranchIdAndAcademicIdAndDateTimeBetween(
                        Long branchId,
                        Long academicId,
                        LocalDateTime start,
                        LocalDateTime end);
}
