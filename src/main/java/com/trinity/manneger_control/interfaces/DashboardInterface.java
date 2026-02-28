package com.trinity.manneger_control.interfaces;

import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.Branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashboardInterface {

    long listarTodosAlunosAtivos(Long academicId);

    long listarTodasFiliaisAtivas(Long academicId);

    long listarTodasAulasHoje(Long academicId);

    long listarTodasPresencasMes(Long academicId);

    long listarAniversariantesMes(Long academicId);


}
