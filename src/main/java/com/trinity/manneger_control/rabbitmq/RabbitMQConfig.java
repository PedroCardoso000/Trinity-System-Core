package com.trinity.manneger_control.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String ALUNO_EXCHANGE = "aluno.exchange";
    public static final String ALUNO_QUEUE = "aluno.created.queue";
    public static final String ALUNO_ROUTING_KEY = "aluno.created";

    public static final String ACADEMIC_EXCHANGE = "trinity.exchange";
    public static final String ACADEMIC_QUEUE = "academic.created.queue";
    public static final String ACADEMIC_ROUTING_KEY = "academic.created";

    public static final String TEACHER_EXCHANGE = "teacher.exchange";
    public static final String TEACHER_QUEUE = "teacher.created.queue";
    public static final String TEACHER_ROUTING_KEY = "teacher.created";

    public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_QUEUE = "user.created.queue";
    public static final String USER_ROUTING_KEY = "user.created";

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
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE);
    }

    @Bean
    public Binding userBinding(
            Queue userQueue,
            TopicExchange userExchange) {

        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with(USER_ROUTING_KEY);
    }

    @Bean
    public Binding binding(Queue alunoQueue, TopicExchange alunoExchange) {
        return BindingBuilder
                .bind(alunoQueue)
                .to(alunoExchange)
                .with(ALUNO_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAlwaysConvertToInferredType(true);
        return converter;
    }

    @Bean
    public TopicExchange teacherExchange() {
        return new TopicExchange(TEACHER_EXCHANGE);
    }

    @Bean
    public Queue teacherQueue() {
        return new Queue(TEACHER_QUEUE);
    }

    @Bean
    public Binding teacherBinding(
            Queue teacherQueue,
            TopicExchange teacherExchange) {

        return BindingBuilder
                .bind(teacherQueue)
                .to(teacherExchange)
                .with(TEACHER_ROUTING_KEY);
    }
}
