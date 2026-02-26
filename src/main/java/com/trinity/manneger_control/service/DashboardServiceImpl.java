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
    public long listarTodosAlunosAtivos(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        return alunoRepository.countByAtivoTrueAndAcademicId(academicId);
    }

    @Override
    public long listarTodasFiliaisAtivas(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        return branchRepository.countByActiveTrueAndAcademicId(academicId);
    }
}
