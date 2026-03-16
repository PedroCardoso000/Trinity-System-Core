package com.trinity.manneger_control.domain.dto;

import com.trinity.manneger_control.domain.Faixas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAlunoAcademicDTO {
    private String nome;
    private String email;
    private Integer quantityDegree;
    private Faixas belt;
    private Long branchId;
    private Long academicId;
    private String branchName;
    private Boolean ativo;
       
}
