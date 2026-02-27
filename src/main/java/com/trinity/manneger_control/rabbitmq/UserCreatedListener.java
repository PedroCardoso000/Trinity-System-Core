package com.trinity.manneger_control.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.trinity.manneger_control.domain.dto.UserCreatedEvent;
import com.trinity.manneger_control.repository.AlunoRepository;
import com.trinity.manneger_control.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class UserCreatedListener {

    private final AlunoRepository alunoRepository;
    private final TeacherRepository teacherRepository;

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void handleUserCreated(UserCreatedEvent event) {

        alunoRepository.findByEmail(event.getEmail())
                .ifPresent(aluno -> {
                    aluno.setUserId(String.valueOf(event.getUserId()));
                    alunoRepository.save(aluno);
                });

        teacherRepository.findByEmail(event.getEmail())
                .ifPresent(teacher -> {
                    teacher.setUserId(String.valueOf(event.getUserId()));
                    teacherRepository.save(teacher);
                });
    }
}
