package ru.daria.serverbeyti.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

class ProducerTest extends AbstractKafkaTest {

    @Autowired
    private Producer producer;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void sendMessage() {
        String message = "Test message";

        producer.sendMessage(message);

        verify(kafkaTemplate).send("topic1", message);
    }
}