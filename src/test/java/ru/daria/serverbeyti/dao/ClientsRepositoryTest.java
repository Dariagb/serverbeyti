package ru.daria.serverbeyti.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ClientsRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private WorkersRepository workersRepository;

    @BeforeEach
    public void setUp() {
        workersRepository.deleteAll();
    }

    @Test
    public void testFindByName() {

        Workers worker1 = Workers.builder().name("Worker 1").build();
        Workers worker2 = Workers.builder().name("Worker 2").build();

        workersRepository.save(worker1);
        workersRepository.save(worker2);

        Client client = Client.builder()
                .name("Test Client")
                .phone("1234567890")
                .workersList(Arrays.asList(worker1, worker2))
                .build();

        clientsRepository.save(client);

        List<Client> clients = clientsRepository.findByName("Test Client");

        assertThat(clients).hasSize(1);
        assertThat(clients.get(0).getName()).isEqualTo("Test Client");
        assertThat(clients.get(0).getWorkersList()).hasSize(2);
        assertThat(clients.get(0).getWorkersList().get(0).getName()).isEqualTo("Worker 1");
        assertThat(clients.get(0).getWorkersList().get(1).getName()).isEqualTo("Worker 2");
    }
}




