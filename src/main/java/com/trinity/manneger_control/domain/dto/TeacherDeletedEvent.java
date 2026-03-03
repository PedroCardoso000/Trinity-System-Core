package com.trinity.manneger_control.domain.dto;

import lombok.Data;

@Data
public class TeacherDeletedEvent {

    private Long teacherId;
    private String email;
}
