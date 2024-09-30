package ru.daria.serverbeyti;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration
@Testcontainers
public class TestBeans {

    @Bean(initMethod = "start",destroyMethod = "stop")
    public PostgreSQLContainer postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:15");
    }
}


