package com.trinity.manneger_control.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.domain.dto.AttendanceResponse;
import com.trinity.manneger_control.domain.dto.CheckInRequest;
import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.Aula;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.AulaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl {
    private final AttendanceRepository attendanceRepository;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;

    public AttendanceResponse checkIn(CheckInRequest request) {

        Aluno aluno = alunoRepository.findById(request.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Aula aula = aulaRepository.findById(request.getAulaId())
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));

        // Regra 1: aluno precisa estar ativo
        if (!aluno.getAtivo()) {
            throw new RuntimeException("Aluno inativo não pode realizar check-in");
        }

        // Regra 2: aluno deve pertencer à mesma branch da aula
        if (!aluno.getBranchId().equals(aula.getBranchId())) {
            throw new RuntimeException("Aluno não pertence à filial da aula");
        }

        // Regra 3: não permitir check-in duplicado
        attendanceRepository.findByAlunoIdAndAulaId(
                request.getAlunoId(),
                request.getAulaId()).ifPresent(a -> {
            throw new RuntimeException("Check-in já realizado");
        });

        Attendance attendance = Attendance.builder()
                .alunoId(aluno.getId())
                .aulaId(aula.getId())
                .status(AttendanceStatus.PRESENT)
                .checkInTime(LocalDateTime.now())
                .build();

        attendanceRepository.save(attendance);


        return new AttendanceResponse(
                attendance.getId(),
                attendance.getAlunoId(),
                attendance.getAulaId(),
                attendance.getStatus().name(),
                attendance.getCheckInTime()
        );

    }

    public void marcarFalta(Long alunoId, Long aulaId) {

        Attendance attendance = attendanceRepository
                .findByAlunoIdAndAulaId(alunoId, aulaId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        attendance.setStatus(AttendanceStatus.ABSENT);

        attendanceRepository.save(attendance);
    }

    public List<Attendance> listarPorAula(Long aulaId) {
        return attendanceRepository.findByAulaId(aulaId);
    }
}
