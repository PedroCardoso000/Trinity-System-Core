package com.trinity.manneger_control.interfaces;

import com.trinity.manneger_control.entity.Aluno;
import com.trinity.manneger_control.entity.Branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashboardInterface {

    long listarTodosAlunosAtivos();

    long listarTodasFiliaisAtivas();

}
