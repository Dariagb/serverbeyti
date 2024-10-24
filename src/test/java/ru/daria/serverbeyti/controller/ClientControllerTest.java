package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dao.ClientsRepository;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.service.ClientService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ClientControllerTest extends AbstractSpringBootTest {
    @Mock
    private ClientService clientService;
    @Mock
    private ClientsRepository clientsRepository;
    @InjectMocks
    private ClientController clientController;


    @Test
    void addClient() {
        Client client = new Client().builder()
                .name("Ольга")
                .phone("6787657765")
                .build();
        when(clientService.createClient(client)).thenReturn(client);
        ResponseEntity<Client> response = clientController.addClient(client);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    @Test
void getClients() {
    List<Client> clients = Arrays.asList(
            Client.builder().name("Катя").phone("899898").build(),
            Client.builder().name("Лида").phone("343434").build()
    );

    when(clientService.readAllClient()).thenReturn(clients);

    ResponseEntity<List<Client>> response = clientController.getClients(); // Вызов контроллера

    assertEquals(HttpStatus.OK, response.getStatusCode()); // Проверка статуса
    assertEquals(clients, response.getBody()); // Проверка содержимого
}


    @Test
    void deleteClient() { Long id = 1L;
        clientController.deleteClient(id);
Mockito.verify(clientService,times(1)).deleteClientsById(id);
    }
}
