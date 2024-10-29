package ru.daria.serverbeyti.dao;

import org.junit.jupiter.api.Test;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkersRepositoryTest extends AbstractSpringBootTest {
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

        Optional<Workers> foundWorker = workersRepository.findBySurname("Романова");

        assertThat(foundWorker).isPresent();
        assertThat(foundWorker.get().getSurname()).isEqualTo("Романова");
    }

    @Test
    public void testFindAllByPost() {
        // Given
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


        assertThat(workers).hasSize(2);
        assertThat(workers).extracting(Workers::getSurname).contains("Котова", "Воронова");
    }

    @Test
    public void testFindWorkersByClientId() {

        Client client = Client.builder().name("Company A").build();
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

