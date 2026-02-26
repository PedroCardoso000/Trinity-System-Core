package com.trinity.manneger_control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByBranchId(Long branchId);

    List<Aluno> findByBranchIdAndAcademiaId(Long branchId, Long academiaId);

    List<Aluno> findByAcademiaId(Long academiaId);
}