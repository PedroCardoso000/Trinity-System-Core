package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.entity.Branch;

public interface BranchInterface {
    Branch criar(Branch branch);

    List<Branch> listarTodos();

    Branch buscarPorId(Long id);

    Branch atualizar(Long id, Branch branchAtualizado);

    void deletar(Long id);
}
