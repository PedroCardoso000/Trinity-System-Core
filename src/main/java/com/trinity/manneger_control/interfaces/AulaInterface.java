package com.trinity.manneger_control.interfaces;

import java.util.List;

import com.trinity.manneger_control.entity.Attendance;
import com.trinity.manneger_control.entity.Aula;

public interface AulaInterface {
    Aula createAula(Aula aula);
    Aula getAulaById(Long id);
    List<Aula> getAllAulas();
    Aula updateAula(Long id, Aula aula);
    void deleteAula(Long id);
    List<Attendance> getAttendanceByAulaId(Long aulaId);
}
