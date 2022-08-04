package com.sofka.broker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    String piso1 = "piso.1.1";

    String piso2 = "piso.2.2";

    String piso3 = "piso.1.3";

    String piso4 = "piso.2.4";

    String porteroExchange = "portero.exchange.topic";

    @Bean
    Queue queueA() {
        return new Queue(piso1, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(piso2, false);
    }

    @Bean
    Queue queueC() {
        return new Queue(piso3, false);
    }

    @Bean
    Queue queueD() {
        return new Queue(piso4, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(porteroExchange);
    }

    @Bean
    Binding bindingA(Queue queueA, TopicExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("routing.1.*");
    }


    @Bean
    Binding bindingB(Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).with("routing.2.2");
    }

    @Bean
    Binding bindingC(Queue queueC, TopicExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange).with("routing.1.*");
    }

    @Bean
    Binding bindingD(Queue queueD, TopicExchange exchange) {
        return BindingBuilder.bind(queueD).to(exchange).with("routing.2.4");
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