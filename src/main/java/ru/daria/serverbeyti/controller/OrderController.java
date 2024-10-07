package ru.daria.serverbeyti.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.kafka.Producer;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final Producer producer;

    @PostMapping("/kafka/send")
    public String send(@RequestBody String message) {
        producer.sendMessage(message);
        return "OK";
    }
}
