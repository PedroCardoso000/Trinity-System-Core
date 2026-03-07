package com.trinity.manneger_control.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trinity.manneger_control.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
      // Conta quantos alunos fazem aniversário no mês
    @Query("SELECT COUNT(a) FROM Aluno a WHERE a.academicId = :academicId " +
            "AND a.ativo = true " +
            "AND MONTH(a.dataNascimento) = :month")
    Long countBirthdaysByMonth(@Param("academicId") Long academicId, @Param("month") int month);
    // Conta quantos alunos ativos tem em um academic
    long countByAtivoTrueAndAcademicId(Long academicId);
    List<Aluno> findByBranchId(Long branchId);

    List<Aluno> findByBranchIdAndAcademicId(Long branchId, Long academicId);

    List<Aluno> findByAcademicId(Long academicId);

    Optional<Aluno> findByEmail(String email);
}