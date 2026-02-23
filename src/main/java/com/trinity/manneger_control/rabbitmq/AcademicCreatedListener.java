package com.trinity.manneger_control.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.trinity.manneger_control.domain.dto.AcademicCreatedEvent;
import com.trinity.manneger_control.entity.Academic;
import com.trinity.manneger_control.repository.AcademicRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AcademicCreatedListener {

    private final AcademicRepository academicRepository;

    @RabbitListener(queues = RabbitMQConfig.ACADEMIC_QUEUE)
    public void handle(AcademicCreatedEvent event) {
        Academic academic = new Academic();
        academic.setName(event.getName());
        academicRepository.save(academic);
    }
}
