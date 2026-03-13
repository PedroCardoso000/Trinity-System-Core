package com.trinity.manneger_control.domain.dto;

import com.trinity.manneger_control.domain.Faixas;

public record GetAlunoAcademicDTO(
    String nome,
    String email,
    Integer quantityDegree,
    Faixas belt,
    Long branchId,
    Long academicId,
    String branchName
) {
    
}
