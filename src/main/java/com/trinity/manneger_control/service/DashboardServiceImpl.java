package com.trinity.manneger_control.service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.interfaces.DashboardInterface;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.AulaRepository;
import com.trinity.manneger_control.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardInterface {

    private final AlunoRepository alunoRepository;
    private final BranchRepository branchRepository;
    private final AulaRepository aulaRepository;
    private final AttendanceRepository attendanceRepository;

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

    @Override
    public long listarTodasAulasHoje(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();

        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);


        return aulaRepository.countByAcademicIdAndDataHoraBetween(academicId, startOfDay, endOfDay);

    }

    @Override
    public long listarTodasPresencasMes(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        // Pega o mês e ano atual
        YearMonth currentMonth = YearMonth.now();

        // Primeiro dia do mês às 00:00:00
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();

        // Último dia do mês às 23:59:59
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(LocalTime.MAX);

        // Chama o repository passando o status de PRESENTE (ajuste conforme seu Enum)
        return attendanceRepository.countMonthlyAttendances(
                academicId,
                AttendanceStatus.PRESENT,
                startOfMonth,
                endOfMonth
        );

    }

    @Override
    public long listarAniversariantesMes(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        // Pega o número do mês atual (1 a 12)
        int currentMonth = LocalDate.now().getMonthValue();

        // Chama o repository passando o ID e o mês calculado
        return alunoRepository.countBirthdaysByMonth(academicId, currentMonth);
    }
}
