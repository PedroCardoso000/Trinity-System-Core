package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.domain.dto.ResponseResult;
import com.trinity.manneger_control.entity.Branch;

public interface BranchInterface {
    ResponseResult criar(Branch branch);

    List<Branch> listarTodos();

    Branch buscarPorId(Long id);

    Branch atualizar(Long id, Branch branchAtualizado);

    void deletar(Long id);

    List<Branch> buscarPorIdAcademia(Long idAcademia);
}
