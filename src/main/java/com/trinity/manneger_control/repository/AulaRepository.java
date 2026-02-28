package com.trinity.manneger_control.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    Long countByAcademicIdAndDataHoraBetween(Long academicId, LocalDateTime start, LocalDateTime end);

    List<Aula> findByBranchId(Long branchId);

}
