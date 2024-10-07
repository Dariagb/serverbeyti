package ru.daria.serverbeyti;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration
@Testcontainers
public class TestBeans {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:15");
    }

    public static final KafkaContainer kafkaContainer = new KafkaContainer("5.2.1")
            .withExposedPorts(9093);

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    static {
        kafkaContainer.start();
    }
}
