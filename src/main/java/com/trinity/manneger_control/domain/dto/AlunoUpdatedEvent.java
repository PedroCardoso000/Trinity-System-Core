package com.trinity.manneger_control.domain.dto;

import lombok.Data;

@Data
public class AlunoUpdatedEvent {

    private Long alunoId;
    private String email;
    private String nome;
    private Boolean ativo;
}
