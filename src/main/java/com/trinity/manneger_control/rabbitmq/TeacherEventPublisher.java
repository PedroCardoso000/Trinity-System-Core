package com.trinity.manneger_control.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.dto.TeacherCreatedEvent;
import com.trinity.manneger_control.entity.Teacher;

import lombok.*;

@Service
@RequiredArgsConstructor
public class TeacherEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishTeacherCreated(Teacher teacher) {

        TeacherCreatedEvent event = new TeacherCreatedEvent();

        event.setTeacherId(teacher.getId());
        event.setEmail(teacher.getEmail());
        event.setName(teacher.getName());
        event.setAcademicId(teacher.getAcademicId());
        event.setBranchId(teacher.getBranchId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TEACHER_EXCHANGE,
                RabbitMQConfig.TEACHER_ROUTING_KEY,
                event);
    }
}
