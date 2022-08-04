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

    public String exchange = "portero_exchange_fanout";

    @PostMapping("/message/send")
    public String publishUser(@RequestBody Correspondencia correspondencia) {
        amqpTemplate.convertAndSend(exchange, "", correspondencia);
        return "Message sent successfully";
    }
}
