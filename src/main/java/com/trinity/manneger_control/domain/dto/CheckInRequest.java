package com.trinity.manneger_control.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckInRequest {

    private Long alunoId;
    private Long classRoomId; // Se já existir no banco
    
    // Para Lazy Generation (se classRoomId for null)
    private Long scheduleId;
    private LocalDateTime dateTime;
}

