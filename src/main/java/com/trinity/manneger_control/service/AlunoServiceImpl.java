package com.trinity.manneger_control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.Faixas;
import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.interfaces.AlunoInterface;
import com.trinity.manneger_control.rabbitmq.AlunoEventPublisher;
import com.trinity.manneger_control.repository.AlunoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoInterface {

    private final AlunoRepository alunoRepository;
    private final AlunoEventPublisher alunoEventPublisher;

    @Override
    public Aluno criar(Aluno aluno) {
        aluno.setId(null);
        aluno.setUserId(null);

        Aluno saved = alunoRepository.save(aluno);

        alunoEventPublisher.publishAlunoCreated(saved);

        return saved;
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

    @Override
    public Aluno updateQuantidadeGraus(Long id, Integer quantidadeGraus) {
        Integer quantidadeGrausVerificada = verifyQuantidadeGraus(id, quantidadeGraus);
        Aluno alunoExistente = buscarPorId(id);
        alunoExistente.setQuantidadeGraus(quantidadeGrausVerificada);
        return alunoRepository.save(alunoExistente);
    }

    @Override
    public Aluno updateFaixa(Long id, Faixas faixaEtaria) {
        Aluno alunoExistente = buscarPorId(id);
        alunoExistente.setFaixa(faixaEtaria);
        return alunoRepository.save(alunoExistente);
    }

    public Integer verifyQuantidadeGraus(Long id, Integer quantidadeGraus) {
        Aluno aluno = buscarPorId(id);
        switch (aluno.getFaixa()) {
            case PRETA:
                if (aluno.getQuantidadeGraus() > 6)
                    throw new IllegalArgumentException("Alunos pretos não podem ter graus");
                return aluno.getQuantidadeGraus() + 1;
            default:
                if (quantidadeGraus == 4 && aluno.getQuantidadeGraus() == 4)
                    throw new IllegalArgumentException("Aluno já atingiu o máximo de graus");
                return aluno.getQuantidadeGraus() + 1;
        }
    }

	@Override
	public List<Aluno> getAlunosByAcademiaId(Long academiaId) {
		return alunoRepository.findByAcademicId(academiaId);
	}

	@Override
	public List<Aluno> getAlunosByBranchIdAndAcademiaId(Long branchId, Long academiaId) {
		return alunoRepository.findByBranchIdAndAcademicId(branchId, academiaId);
	}
}
