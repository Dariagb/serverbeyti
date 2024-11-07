package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class WorkersRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private WorkersRepository workersRepository;

    @Autowired
    private ClientsRepository clientRepository;
    @BeforeEach
    public void setUp() {
        workersRepository.deleteAll();
    }
    @Test
    public void testFindBySurname() {
        Workers worker = Workers.builder()
                .name("Полина")
                .surname("Романова")
                .post("Парикмахер")
                .age(30)
                .phone("123-456-7890")
                .build();
        workersRepository.save(worker);

        List<Workers> foundWorkers = workersRepository.findBySurname("Романова");
        assertThat(foundWorkers).isNotEmpty();
        assertThat(foundWorkers.get(0).getSurname()).isEqualTo("Романова");
    }

    @Test
    public void testFindAllByPost() {

        Workers worker1 = Workers.builder()
                .name("Алиса")
                .surname("Котова")
                .post("мастер маникюра")
                .age(28)
                .phone("123-456-7891")
                .build();
        Workers worker2 = Workers.builder()
                .name("Ирина")
                .surname("Воронова")
                .post("парикмахер")
                .age(35)
                .phone("123-456-7892")
                .build();

        workersRepository.save(worker1);
        workersRepository.save(worker2);

        List<Workers> workers = workersRepository.findAllByPost("мастер маникюра");

        assertThat(workers).isNotEmpty();
        assertThat(workers).hasSize(1);
        assertThat(workers.get(0).getPost()).isEqualTo("мастер маникюра");
        assertThat(workers.get(0).getSurname()).isEqualTo(worker1.getSurname());
    }

    @Test
    public void testFindWorkersByClientId() {
        Client client = Client.builder().name("Company A").build();
        client = clientRepository.save(client);

        Workers worker = Workers.builder()
                .name("Вера")
                .surname("Богачева")
                .post("админ")
                .age(40)
                .phone("123-456-7893")
                .clients(List.of(client))
                .build();
        workersRepository.save(worker);

        List<Workers> workers = workersRepository.findWorkersByClientId(client.getId());

        assertThat(workers).isNotEmpty();
        assertThat(workers.get(0).getClients()).contains(client);
    }
}

