package ru.daria.serverbeyti.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaConfigurationTest extends AbstractKafkaTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldCreateTopic() throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        AdminClient adminClient = AdminClient.create(props);

        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> topics = listTopicsResult.names().get();

        assertThat(topics).contains("topic1");
    }

    @Test
    void shouldFindKafkaTemplate() {
        assertThat(applicationContext.getBean(KafkaTemplate.class)).isNotNull();
    }

    @Test
    void shouldFindConsumerFactory() {
        assertThat(applicationContext.getBean(ConsumerFactory.class)).isNotNull();
    }

    @Test
    void shouldFindKafkaListenerContainerFactory() {
        assertThat(applicationContext.getBean(KafkaListenerContainerFactory.class)).isNotNull();
    }
}