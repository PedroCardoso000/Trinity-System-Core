package com.trinity.manneger_control.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    // Total de presenças confirmadas no mês atual em toda a rede
    @Query("SELECT COUNT(at) FROM Attendance at " +
            "JOIN ClassRoom cr ON at.classRoomId = cr.id " +
            "WHERE cr.academicId = :academicId " +
            "AND at.status = :status " +
            "AND at.checkInTime BETWEEN :start AND :end")
    Long countMonthlyAttendances(
            @Param("academicId") Long academicId,
            @Param("status") AttendanceStatus status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    Optional<Attendance> findByAlunoIdAndClassRoomId(Long alunoId, Long classRoomId);

    List<Attendance> findByClassRoomId(Long classRoomId);

    List<Attendance> findByAlunoId(Long alunoId);
}
