package com.trinity.manneger_control.repository;

import com.trinity.manneger_control.entity.AlunoGraduationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoGraduationHistoryRepository extends JpaRepository<AlunoGraduationHistory, Long> {
    List<AlunoGraduationHistory> findByAlunoIdOrderByDataGraduacaoDesc(Long alunoId);
}
