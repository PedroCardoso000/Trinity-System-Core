package com.trinity.manneger_control.entity;

import com.trinity.manneger_control.domain.Faixas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, comment = "Nome do aluno")
    private String nome;

    @Column(nullable = false, unique = true, comment = "Email do aluno")
    private String email;

    private String telefone;
    private Integer anoInicioNaTrinity;

    @Column(nullable = false, comment = "Faixa etária do aluno")
    private Faixas faixa;

    private Integer quantidadeGraus;

    @Column(nullable = false, comment = "Indica se o aluno está ativo na Trinity")
    private Boolean ativo;

    private String userId;

    // Apenas referência
    @Column(nullable = false, comment = "ID da branch à qual o aluno está associado")
    private Long branchId;

    @Column(nullable = false, comment = "ID do academic à qual o aluno está associado")
    private Long academicId;

}
