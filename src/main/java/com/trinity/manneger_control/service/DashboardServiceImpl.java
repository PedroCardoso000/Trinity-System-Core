package com.trinity.manneger_control.service;

import com.trinity.manneger_control.domain.AttendanceStatus;
import com.trinity.manneger_control.interfaces.DashboardInterface;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;
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
    private final ClassRoomRepository classRoomRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public long countAllActiveStudents(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        long activeStudentCount = alunoRepository.countByAtivoTrueAndAcademicId(academicId);

        return activeStudentCount;
    }

    @Override
    public long countAllActiveBranchess(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }

        long activeBranchCount = branchRepository.countByActiveTrueAndAcademicId(academicId);

        return activeBranchCount;
    }

    @Override
    public long countAllLessonsToday(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();

        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        long dailyLessonCount = classRoomRepository.countByAcademicIdAndDateTimeBetween(academicId, startOfDay, endOfDay);

        return dailyLessonCount;
    }

    @Override
    public long countAllMonthlyPresences(Long academicId) {

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
        long monthlyPresenceCount = attendanceRepository.countMonthlyAttendances(
                academicId,
                AttendanceStatus.PRESENT,
                startOfMonth,
                endOfMonth
        );
        return monthlyPresenceCount;

    }

    @Override
    public long countAllMonthlyBirthdays(Long academicId) {

        if (academicId == null) {
            throw new IllegalArgumentException("Academic ID cannot be null");
        }
        // Pega o número do mês atual (1 a 12)
        int currentMonth = LocalDate.now().getMonthValue();

        // Chama o repository passando o ID e o mês calculado
        long monthlyBirthdayCount = alunoRepository.countBirthdaysByMonth(academicId, currentMonth);

        return monthlyBirthdayCount;
    }
}
