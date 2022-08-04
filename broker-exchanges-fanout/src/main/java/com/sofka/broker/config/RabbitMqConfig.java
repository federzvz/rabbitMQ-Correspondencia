package com.sofka.broker.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    String piso1Queue = "piso.1.1";

    String piso2Queue = "piso.2.2";

    String piso3Queue = "piso.1.3";

    String piso4Queue = "piso.2.4";

    String porteroExchange = "portero_exchange_fanout";

    @Bean
    Queue piso1Queue() {
        return new Queue(piso1Queue, false);
    }

    @Bean
    Queue piso2Queue() {
        return new Queue(piso2Queue, false);
    }

    @Bean
    Queue piso3Queue() {
        return new Queue(piso3Queue, false);
    }

    @Bean
    Queue piso4Queue() {
        return new Queue(piso4Queue, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(porteroExchange);
    }

    @Bean
    Binding piso1Binding(Queue piso1Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(piso1Queue).to(exchange);
    }

    @Bean
    Binding piso2Binding(Queue piso2Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(piso2Queue).to(exchange);
    }

    @Bean
    Binding piso3Binding(Queue piso3Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(piso3Queue).to(exchange);
    }

    @Bean
    Binding piso4Binding(Queue piso4Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(piso4Queue).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}