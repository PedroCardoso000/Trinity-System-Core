package com.trinity.manneger_control.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByAlunoIdAndClassRoomId(Long alunoId, Long classRoomId);

    List<Attendance> findByClassRoomId(Long classRoomId);

    List<Attendance> findByAlunoId(Long alunoId);
}
