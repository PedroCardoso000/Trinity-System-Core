package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.domain.dto.GraduationHistoryResponse;
import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.AlunoGraduationHistory;

public interface AlunoInterface {
    Aluno criar(Aluno aluno);

    List<Aluno> listarTodos();

    Aluno buscarPorId(Long id);

    Aluno atualizar(Long id, Aluno alunoAtualizado);

    void deletar(Long id);

    Aluno updateQuantidadeGraus(Long id, Integer quantidadeGraus);

    Aluno updateBeltAndQuantityDegree(Long id, Faixas faixa, Integer quantidadeGraus);

    List<Aluno> getAlunosByAcademiaId(Long academiaId);

    List<Aluno> getAlunosByBranchIdAndAcademiaId(Long branchId, Long academiaId);

    List<AlunoGraduationHistory> getGraduationHistory(Long alunoId);

    List<AlunoGraduationHistory> getAllGraduationHistory();

    List<GraduationHistoryResponse> getGraduationHistoryDTO(Long alunoId);

    List<GraduationHistoryResponse> getAllGraduationHistoryDTO();

}
