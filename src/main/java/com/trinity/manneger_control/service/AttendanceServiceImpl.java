package com.trinity.manneger_control.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.domain.dto.AttendaceDto;
import com.trinity.manneger_control.domain.dto.AttendanceResponse;
import com.trinity.manneger_control.domain.dto.CheckInDto;
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

    public CheckInDto checkIn(Long alunoId, Long classRoomId) {

        Attendance attendance = attendanceRepository
                .findByAlunoIdAndClassRoomId(alunoId, classRoomId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (attendance.getStatus() == AttendanceStatus.PRESENT) {
            return new CheckInDto(
                    alunoId,
                    classRoomId,
                    "Check-in já realizado");
        }

        attendance.setStatus(AttendanceStatus.PRESENT);
        attendance.setCheckInTime(LocalDateTime.now());

        attendanceRepository.save(attendance);

        return new CheckInDto(
                alunoId,
                classRoomId,
                "Check-in realizado com sucesso");
    }

    public AttendanceResponse marcarPendente(CheckInRequest request) {

        Aluno aluno = alunoRepository.findById(request.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));

        validateCheckIn(aluno, classRoom);

        Attendance attendance = Attendance.builder()
                .alunoId(aluno.getId())
                .classRoomId(classRoom.getId())
                .status(AttendanceStatus.PENDANT)
                .checkInTime(LocalDateTime.now())
                .build();

        attendanceRepository.save(attendance);

        return mapToResponse(attendance);
    }

    private void validateCheckIn(Aluno aluno, ClassRoom classRoom) {

        if (classRoom.getCancelled()) {
            throw new RuntimeException("Aula cancelada");
        }

        if (!aluno.getAtivo()) {
            throw new RuntimeException("Aluno inativo");
        }

        if (!aluno.getBranchId().equals(classRoom.getBranchId())) {
            throw new RuntimeException("Aluno não pertence à filial da aula");
        }

        attendanceRepository
                .findByAlunoIdAndClassRoomId(aluno.getId(), classRoom.getId())
                .ifPresent(a -> {
                    throw new RuntimeException("Check-in já realizado");
                });
    }

    private AttendanceResponse mapToResponse(Attendance attendance) {

        AttendanceResponse response = new AttendanceResponse();

        response.setId(attendance.getId());
        response.setAlunoId(attendance.getAlunoId());
        response.setClassRoomId(attendance.getClassRoomId());
        response.setStatus(attendance.getStatus().name());
        response.setCheckInTime(attendance.getCheckInTime());

        return response;
    }

    public void marcarFalta(Long alunoId, Long classRoomId) {

        Attendance attendance = attendanceRepository
                .findByAlunoIdAndClassRoomId(alunoId, classRoomId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        attendance.setStatus(AttendanceStatus.ABSENT);

        attendanceRepository.save(attendance);
    }

    public List<AttendaceDto> listarPorAula(Long classRoomId) {
        List<AttendaceDto> attendaceDtos = new ArrayList<>();
        List<Attendance> attendances = attendanceRepository.findByClassRoomId(classRoomId);
        for (Attendance attendance : attendances) {
            Aluno aluno = alunoRepository.findById(attendance.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            attendaceDtos.add(new AttendaceDto(
                    attendance.getId(),
                    aluno.getNome(),
                    attendance.getClassRoomId(),
                    attendance.getStatus(),
                    attendance.getCheckInTime()));
        }

        return attendaceDtos;
    }
}
