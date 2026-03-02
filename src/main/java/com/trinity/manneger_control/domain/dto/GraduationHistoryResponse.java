package com.trinity.manneger_control.domain.dto;

import com.trinity.manneger_control.domain.Faixas;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraduationHistoryResponse {
    private Long id;
    private LocalDateTime dataGraduacao;
    private Faixas faixa;
    private Integer quantidadeGraus;
    private String observacao;
    
    // Dados do Aluno
    private Long alunoId;
    private String alunoNome;
    
    // Dados da Filial (Branch)
    private Long branchId;
    private String branchNome;
    
    // Dados da Academia (Academic)
    private Long academicId;
    private String academicNome;
}
