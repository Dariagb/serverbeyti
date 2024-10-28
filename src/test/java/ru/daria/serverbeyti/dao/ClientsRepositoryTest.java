package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.model.Client;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
class ClientsRepositoryTest extends AbstractSpringBootTest {

    @Test
    public void testFindByName() {

        Client client = Client.builder()
                .name("Иван")
                .phone("89898")
                .build();
        Client client2 = Client.builder()
                .name("Артем")
                .phone("89844")
                .build();
        clientsRepository.save(client);
        clientsRepository.save(client2);

        List<Client> foundClients = clientsRepository.findByName("Иван");

        assertFalse(foundClients.isEmpty(), "Список пустой");
        assertEquals("Иван", foundClients.get(0).getName());
        assertEquals(client.getPhone(), foundClients.get(0).getPhone());
    }
}

