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
