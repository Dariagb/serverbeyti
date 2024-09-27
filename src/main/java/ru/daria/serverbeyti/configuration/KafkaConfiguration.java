package ru.daria.serverbeyti.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic topic1() {
        return new NewTopic("topic1", 1, (short) 1);
    }
}
