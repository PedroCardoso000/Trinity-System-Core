package com.trinity.manneger_control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.interfaces.AlunoInterface;
import com.trinity.manneger_control.repository.AlunoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoInterface {

    private final AlunoRepository alunoRepository;

    @Override
    public Aluno criar(Aluno aluno) {
        aluno.setId(null);
        return alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
    }

    @Override
    public Aluno atualizar(Long id, Aluno alunoAtualizado) {

        Aluno alunoExistente = buscarPorId(id);

        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setEmail(alunoAtualizado.getEmail());
        alunoExistente.setTelefone(alunoAtualizado.getTelefone());
        alunoExistente.setAnoInicioNaTrinity(alunoAtualizado.getAnoInicioNaTrinity());
        alunoExistente.setFaixa(alunoAtualizado.getFaixa());
        alunoExistente.setQuantidadeGraus(alunoAtualizado.getQuantidadeGraus());
        alunoExistente.setAtivo(alunoAtualizado.getAtivo());
        alunoExistente.setUserId(alunoAtualizado.getUserId());

        return alunoRepository.save(alunoExistente);
    }

    @Override
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}
