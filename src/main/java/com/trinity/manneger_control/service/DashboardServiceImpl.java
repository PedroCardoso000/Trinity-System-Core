package com.trinity.manneger_control.service;

import com.trinity.manneger_control.interfaces.DashboardInterface;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardInterface {

    private final AlunoRepository alunoRepository;
    private final BranchRepository branchRepository;

    @Override
    public long listarTodosAlunosAtivos() {
        return alunoRepository.countByActiveTrue();
    }

    @Override
    public long listarTodasFiliaisAtivas() {
        return branchRepository.countByActiveTrue();
    }
}
