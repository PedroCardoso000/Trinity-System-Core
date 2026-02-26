package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.Aluno;

public interface AlunoInterface {
    Aluno criar(Aluno aluno);

    List<Aluno> listarTodos();

    Aluno buscarPorId(Long id);

    Aluno atualizar(Long id, Aluno alunoAtualizado);

    void deletar(Long id);

    Aluno updateQuantidadeGraus(Long id, Integer quantidadeGraus);

    Aluno updateFaixa(Long id, Faixas faixaEtaria);

    List<Aluno> getAlunosByAcademiaId(Long academiaId);

    List<Aluno> getAlunosByBranchIdAndAcademiaId(Long branchId, Long academiaId);

}
