package com.trinity.manneger_control.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicCreatedEvent {
    private Long academicId;
    private String name;
}
