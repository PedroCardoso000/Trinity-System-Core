package com.trinity.manneger_control.repository;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Total de presenças confirmadas no mês atual em toda a rede
    @Query("SELECT COUNT(at) FROM Attendance at " +
            "JOIN Aula au ON at.aulaId = au.id " +
            "WHERE au.academicId = :admId " +
            "AND at.status = :status " +
            "AND at.checkInTime BETWEEN :start AND :end")
    Long countMonthlyAttendances(
            @Param("admId") Long admId,
            @Param("status") AttendanceStatus status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    Optional<Attendance> findByAlunoIdAndAulaId(Long alunoId, Long aulaId);

    List<Attendance> findByAulaId(Long aulaId);

    List<Attendance> findByAlunoId(Long alunoId);
}
