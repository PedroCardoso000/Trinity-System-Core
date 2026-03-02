package com.trinity.manneger_control.entity;

import com.trinity.manneger_control.domain.Faixas;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "aluno_graduation_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoGraduationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long alunoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Faixas faixa;

    @Column(nullable = false)
    private Integer quantidadeGraus;

    @Column(nullable = false)
    private LocalDateTime dataGraduacao;

    private String observacao;
}
