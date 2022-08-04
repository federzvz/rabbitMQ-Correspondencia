package com.sofka.broker.controller;

import com.sofka.broker.model.Correspondencia;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String exchange = "portero.exchange.topic";

    @PostMapping("/message/sendImpar")
    public String publishUser(@RequestBody Correspondencia message) {
        amqpTemplate.convertAndSend(exchange, "routing.1.*", message);
        return "Message sent successfully";
    }
}
