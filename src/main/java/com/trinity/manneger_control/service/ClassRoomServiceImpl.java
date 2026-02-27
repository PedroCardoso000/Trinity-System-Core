package com.trinity.manneger_control.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.ClassRoom;
import com.trinity.manneger_control.interfaces.ClassRoomInterface;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.ClassRoomRepository;

@Service
public class ClassRoomServiceImpl implements ClassRoomInterface {

    private final ClassRoomRepository classRoomRepository;
    private final AttendanceRepository attendanceRepository;

    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository, AttendanceRepository attendanceRepository) {
        this.classRoomRepository = classRoomRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public ClassRoom createClassRoom(ClassRoom classEntity) {
        return classRoomRepository.save(classEntity);
    }

    @Override
    public ClassRoom getClassRoomById(Long id) {
        return classRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala de aula não encontrada"));
    }

    @Override
    public List<ClassRoom> getAllClasses() {
        return classRoomRepository.findAll();
    }

    @Override
    public ClassRoom updateClassRoom(Long id, ClassRoom classRoom) {
        ClassRoom classRoomExistente = classRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala de aula não encontrada"));
        classRoomExistente.setDataHora(classRoom.getDataHora());
        classRoomExistente.setAtiva(classRoom.getAtiva());
        classRoomExistente.setBranchId(classRoom.getBranchId());
        classRoomExistente.setNome(classRoom.getNome());
        return classRoomRepository.save(classRoomExistente);
    }


    @Override
    public void deleteClassRoom(Long id) {
        classRoomRepository.deleteById(id);  
    }

    @Override
    public List<Attendance> getAttendanceByClassRoomId(Long classRoomId) {
        return attendanceRepository.findByClassRoomId(classRoomId);
    }
    
}

