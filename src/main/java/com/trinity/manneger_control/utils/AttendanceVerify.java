package com.trinity.manneger_control.utils;

import org.springframework.stereotype.Component;

import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AttendanceVerify {
    private final AlunoRepository alunoRepository;
    private final ClassRoomRepository classRoomRepository;

    public void verifyMatchStudentAndClassRoom(Aluno aluno, ClassRoom classRoom) {

        // Verification Student
        if (!aluno.getAtivo()) {
            throw new RuntimeException("Student not in class");
        }

        // Verification ClassRoom
        if (classRoom.getCancelled()) {
            throw new RuntimeException("Class cancelled");
        }

        // Both Verification

        if (!classRoom.getAcademicId().equals(aluno.getAcademicId())) {
            throw new RuntimeException("Student not in Academic");
        }

        if (!classRoom.getBranchId().equals(aluno.getBranchId())) {
            throw new RuntimeException("Student not in Branch");
        }

    }
}
