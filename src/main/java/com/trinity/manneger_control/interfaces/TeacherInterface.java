package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.Teacher;

public interface TeacherInterface {
    Teacher criar(Teacher teacher);

    List<Teacher> listarTodos();

    Teacher buscarPorId(Long id);

    Teacher atualizar(Long id, Teacher teacherAtualizado);

    void deletar(Long id);

    Teacher updateQuantidadeGraus(Long id, Integer quantidadeGraus);

    Teacher updateFaixa(Long id, Faixas faixaEtaria);

    List<Teacher> getTeachersByAcademiaId(Long academiaId);

    List<Teacher> getTeachersByBranchIdAndAcademiaId(Long branchId, Long academiaId);

}
