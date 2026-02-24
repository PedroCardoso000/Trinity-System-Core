package com.trinity.manneger_control.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.dto.AlunoCreatedEvent;
import com.trinity.manneger_control.entity.Aluno;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AlunoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishAlunoCreated(Aluno aluno) {

        AlunoCreatedEvent event = new AlunoCreatedEvent(
                aluno.getId(),
                aluno.getEmail(),
                aluno.getNome(),
                aluno.getAcademicId(),
                aluno.getBranchId()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ALUNO_EXCHANGE,
                RabbitMQConfig.ALUNO_ROUTING_KEY,
                event
        );
    }
}
