package com.trinity.manneger_control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.Aula;
import com.trinity.manneger_control.interfaces.AulaInterface;
import com.trinity.manneger_control.repository.AttendanceRepository;
import com.trinity.manneger_control.repository.AulaRepository;

@Service
public class AulaServiceImpl implements AulaInterface {

    private final AulaRepository aulaRepository;
    private final AttendanceRepository attendanceRepository;

    public AulaServiceImpl(AulaRepository aulaRepository, AttendanceRepository attendanceRepository) {
        this.aulaRepository = aulaRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Aula createAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    @Override
    public Aula getAulaById(Long id) {
        return aulaRepository.findById(id).orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    @Override
    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }

    @Override
    public Aula updateAula(Long id, Aula aula) {
        Aula aulaExistente = aulaRepository.findById(id).orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        aulaExistente.setDataHora(aula.getDataHora());
        aulaExistente.setAtiva(aula.getAtiva());
        aulaExistente.setBranchId(aula.getBranchId());
        aulaExistente.setNome(aula.getNome());
        return aulaRepository.save(aulaExistente);
    }

    @Override
    public void deleteAula(Long id) {
        aulaRepository.deleteById(id);  
    }

    @Override
    public List<Attendance> getAttendanceByAulaId(Long aulaId) {
        return attendanceRepository.findByAulaId(aulaId);
    }
    
}
