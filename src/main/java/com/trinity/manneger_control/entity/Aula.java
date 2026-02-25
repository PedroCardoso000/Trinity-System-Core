package com.trinity.manneger_control.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "aula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Boolean ativa;

    // Referência simples
    @Column(nullable = false)
    private Long branchId;

    // Referência simples
    @Column(nullable = false)
    private Long academicId;
}
