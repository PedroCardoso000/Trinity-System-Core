package com.trinity.manneger_control.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.trinity.manneger_control.domain.dto.GraduationHistoryResponse;
import com.trinity.manneger_control.entity.Academic;
import com.trinity.manneger_control.entity.AlunoGraduationHistory;
import com.trinity.manneger_control.entity.Branch;
import com.trinity.manneger_control.repository.AcademicRepository;
import com.trinity.manneger_control.repository.AlunoGraduationHistoryRepository;
import com.trinity.manneger_control.repository.BranchRepository;
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
    private final AlunoGraduationHistoryRepository graduationHistoryRepository;
    private final BranchRepository branchRepository;
    private final AcademicRepository academicRepository;

    @Override
    public Aluno criar(Aluno aluno) {
        aluno.setId(null);
        aluno.setUserId(null);

        Aluno saved = alunoRepository.save(aluno);

        // Registrar graduação inicial
        registrarHistorico(saved, "Graduação inicial no cadastro");

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
        Aluno saved = alunoRepository.save(alunoExistente);

        registrarHistorico(saved, "Atualização de graus");

        return saved;
    }

    @Override
    public Aluno updateBeltAndQuantityDegree(Long id, Faixas faixa, Integer quantidadeGraus) {
        Aluno alunoExistente = buscarPorId(id);
        alunoExistente.setFaixa(faixa);
        alunoExistente.setQuantidadeGraus(verifyQuantidadeGraus(id, quantidadeGraus));
        Aluno saved = alunoRepository.save(alunoExistente);

        registrarHistorico(saved, "Atualização de faixa e graus");

        return saved;
    }

    private void registrarHistorico(Aluno aluno, String observacao) {
        AlunoGraduationHistory history = AlunoGraduationHistory.builder()
                .alunoId(aluno.getId())
                .faixa(aluno.getFaixa())
                .quantidadeGraus(aluno.getQuantidadeGraus())
                .dataGraduacao(LocalDateTime.now())
                .observacao(observacao)
                .build();
        graduationHistoryRepository.save(history);
    }

    @Override
    public List<AlunoGraduationHistory> getGraduationHistory(Long alunoId) {
        return graduationHistoryRepository.findByAlunoIdOrderByDataGraduacaoDesc(alunoId);
    }

    @Override
    public List<AlunoGraduationHistory> getAllGraduationHistory() {
        return graduationHistoryRepository.findAll();
    }

    @Override
    public List<GraduationHistoryResponse> getGraduationHistoryDTO(Long alunoId) {
        List<AlunoGraduationHistory> histories = graduationHistoryRepository.findByAlunoIdOrderByDataGraduacaoDesc(alunoId);
        return histories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GraduationHistoryResponse> getAllGraduationHistoryDTO() {
        List<AlunoGraduationHistory> histories = graduationHistoryRepository.findAll();
        return histories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GraduationHistoryResponse convertToDTO(AlunoGraduationHistory history) {
        Aluno aluno = alunoRepository.findById(history.getAlunoId())
                .orElse(null);
        
        String alunoNome = (aluno != null) ? aluno.getNome() : "Aluno não encontrado";
        Long branchId = (aluno != null) ? aluno.getBranchId() : null;
        Long academicId = (aluno != null) ? aluno.getAcademicId() : null;

        String branchNome = "N/A";
        if (branchId != null) {
            branchNome = branchRepository.findById(branchId)
                    .map(Branch::getName)
                    .orElse("Filial não encontrada");
        }

        String academicNome = "N/A";
        if (academicId != null) {
            academicNome = academicRepository.findById(academicId)
                    .map(Academic::getName)
                    .orElse("Academia não encontrada");
        }

        return GraduationHistoryResponse.builder()
                .id(history.getId())
                .dataGraduacao(history.getDataGraduacao())
                .faixa(history.getFaixa())
                .quantidadeGraus(history.getQuantidadeGraus())
                .observacao(history.getObservacao())
                .alunoId(history.getAlunoId())
                .alunoNome(alunoNome)
                .branchId(branchId)
                .branchNome(branchNome)
                .academicId(academicId)
                .academicNome(academicNome)
                .build();
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
