package com.trinity.manneger_control.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.domain.dto.AttendanceResponse;
import com.trinity.manneger_control.domain.dto.CheckInRequest;
import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl {
    private final AttendanceRepository attendanceRepository;
    private final AlunoRepository alunoRepository;
    private final ClassRoomRepository classRoomRepository;

    public AttendanceResponse checkIn(CheckInRequest request) {

        Aluno aluno = alunoRepository.findById(request.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Sala de aula não encontrada"));

        // Regra 1: aluno precisa estar ativo
        if (!aluno.getAtivo()) {
            throw new RuntimeException("Aluno inativo não pode realizar check-in");
        }

        // Regra 2: aluno deve pertencer à mesma branch da aula
        if (!aluno.getBranchId().equals(classRoom.getBranchId())) {
            throw new RuntimeException("Aluno não pertence à filial da aula");
        }

        // Regra 3: não permitir check-in duplicado
        attendanceRepository.findByAlunoIdAndClassRoomId(
                request.getAlunoId(),
                request.getClassRoomId()).ifPresent(a -> {
            throw new RuntimeException("Check-in já realizado");
        });

        Attendance attendance = new Attendance();

        attendance.setAlunoId(aluno.getId());
        attendance.setClassRoomId(classRoom.getId());
        attendance.setStatus(AttendanceStatus.PRESENT);
        attendance.setCheckInTime(LocalDateTime.now());

        attendanceRepository.save(attendance);


        AttendanceResponse attendanceResponse = new AttendanceResponse();

        attendanceResponse.setId(attendance.getId());
        attendanceResponse.setAlunoId(attendance.getAlunoId());
        attendanceResponse.setClassRoomId(attendance.getClassRoomId());
        attendanceResponse.setStatus(attendance.getStatus().name());
        attendanceResponse.setCheckInTime(attendance.getCheckInTime());


        return attendanceResponse;

    }

    public void marcarFalta(Long alunoId, Long classRoomId) {

        Attendance attendance = attendanceRepository
                .findByAlunoIdAndClassRoomId(alunoId, classRoomId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        attendance.setStatus(AttendanceStatus.ABSENT);

        attendanceRepository.save(attendance);
    }

    public List<Attendance> listarPorAula(Long classRoomId) {
        return attendanceRepository.findByClassRoomId(classRoomId);
    }
}
