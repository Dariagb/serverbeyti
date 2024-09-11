package ru.daria.serverbeyti.kafka;


import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final Producer producer;
    public OrderController(Producer producer) {
        this.producer = producer;
    }
    @PostMapping("/kafka/send")
    public String send(@RequestBody String message) {
        producer.sendMessage(message);
        return "OK";
    }
}
