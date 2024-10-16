package ru.daria.serverbeyti.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import ru.daria.serverbeyti.AbstractSpringBootTest;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.daria.serverbeyti.TestBeans.kafkaContainer;

class KafkaConfigurationTest extends AbstractSpringBootTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldCreateTopic() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        AdminClient adminClient = AdminClient.create(props);

        NewTopic newTopic = new NewTopic("topic1", 1, (short) 1);
        adminClient.createTopics(Collections.singleton(newTopic)).all().get();

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