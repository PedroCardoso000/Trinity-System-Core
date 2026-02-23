package com.trinity.manneger_control.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ALUNO_EXCHANGE = "aluno.exchange";
    public static final String ALUNO_QUEUE = "aluno.created.queue";
    public static final String ALUNO_ROUTING_KEY = "aluno.created";
    
    public static final String ACADEMIC_EXCHANGE = "trinity.exchange";
    public static final String ACADEMIC_QUEUE = "academic.created.queue";
    public static final String ACADEMIC_ROUTING_KEY = "academic.created";

    @Bean
    public TopicExchange academicExchange() {
        return new TopicExchange(ACADEMIC_EXCHANGE);
    }

    @Bean
    public Queue academicQueue() {
        return new Queue(ACADEMIC_QUEUE);
    }

    @Bean
    public Binding academicBinding(
            Queue academicQueue,
            TopicExchange academicExchange) {

        return BindingBuilder
                .bind(academicQueue)
                .to(academicExchange)
                .with(ACADEMIC_ROUTING_KEY);
    }

    @Bean
    public TopicExchange alunoExchange() {
        return new TopicExchange(ALUNO_EXCHANGE);
    }

    @Bean
    public Queue alunoQueue() {
        return new Queue(ALUNO_QUEUE);
    }

    @Bean
    public Binding binding(Queue alunoQueue, TopicExchange alunoExchange) {
        return BindingBuilder
                .bind(alunoQueue)
                .to(alunoExchange)
                .with(ALUNO_ROUTING_KEY);
    }
}
