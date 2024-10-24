package ru.daria.serverbeyti.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.daria.serverbeyti.dao.ClientsRepository;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;
import ru.daria.serverbeyti.service.ClientService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientsRepository clientsRepository;

    @Mock
    private WorkersRepository workersRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void createClient() {
        Client client = new Client();
        when(clientsRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        assertEquals(client, createdClient);
        verify(clientsRepository, times(1)).save(client);
    }

    @Test
    void readAllClient() {
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientsRepository.findAll()).thenReturn(clients);

        List<Client> allClients = clientService.readAllClient();

        assertEquals(clients, allClients);
        verify(clientsRepository, times(1)).findAll();
    }

    @Test
    void deleteClientsById() {
        Long clientId = 1L;
        clientService.deleteClientsById(clientId);

        verify(clientsRepository, times(1)).deleteById(clientId);
    }

    @Test
    void getWorkers() {
        Long clientId = 1L;
        List<Workers> workers = Arrays.asList(new Workers(), new Workers());
        when(workersRepository.findWorkersByClientId(clientId)).thenReturn(workers);

        List<Workers> foundWorkers = clientService.getWorkers(clientId);

        assertEquals(workers, foundWorkers);
        verify(workersRepository, times(1)).findWorkersByClientId(clientId);
    }

    @Test
    void findByNameClient() {
        String name = "Test Client";
        List<Client> clients = Arrays.asList(new Client(), new Client());
        when(clientsRepository.findByName(name)).thenReturn(clients);

        List<Client> foundClients = clientService.findByNameClient(name);

        assertEquals(clients, foundClients);
        verify(clientsRepository, times(1)).findByName(name);
    }
}