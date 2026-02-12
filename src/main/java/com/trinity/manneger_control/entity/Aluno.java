package com.trinity.manneger_control.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados principais
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, comment = "Email do Aluno inválido")
    private String email;
    
    private String telefone;

    @Column(name = "ano_inicio")
    private Integer anoInicioNaTrinity;

    @Column(nullable = false, comment = "Faixa do Aluno inválida")
    private String faixa;

    @Column(name = "quantidade_graus")
    private Integer quantidadeGraus;

    // Status (ativo / inativo)
    @Column(nullable = false, comment = "Status do Aluno inválido")
    private Boolean ativo;

    @Column(name = "user_id", unique = true)
    private Long userId;

}
