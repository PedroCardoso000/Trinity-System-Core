package com.trinity.manneger_control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Branch;
import com.trinity.manneger_control.interfaces.BranchInterface;
import com.trinity.manneger_control.repository.AcademicRepository;
import com.trinity.manneger_control.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchInterface {
    private final BranchRepository branchRepository;
    private final AcademicRepository academicRepository;

    @Override
    public Branch criar(Branch branch) {

        if (!academicRepository.existsById(branch.getAcademicId())) {
            throw new RuntimeException("Academic não encontrada");
        }

        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> listarTodos() {
        return branchRepository.findAll();
    }

    @Override
    public Branch buscarPorId(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new RuntimeException("Branch não encontrado"));
    }

    @Override
    public Branch atualizar(Long id, Branch branchAtualizado) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new RuntimeException("Branch não encontrado"));
        branch.setName(branchAtualizado.getName());
        branch.setAddress(branchAtualizado.getAddress());
        branch.setCity(branchAtualizado.getCity());
        branch.setState(branchAtualizado.getState());
        branch.setCountry(branchAtualizado.getCountry());
        branch.setZipCode(branchAtualizado.getZipCode());
        branch.setPhone(branchAtualizado.getPhone());
        branch.setActive(branchAtualizado.getActive());
        return branchRepository.save(branch);
    }

    @Override
    public void deletar(Long id) {
        branchRepository.deleteById(id);
    }
}
