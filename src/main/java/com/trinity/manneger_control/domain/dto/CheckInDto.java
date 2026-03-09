package com.trinity.manneger_control.domain.dto;

public record CheckInDto(
        Long alunoId,
        Long classRoomId,
        String mensagem
) {
    
}
