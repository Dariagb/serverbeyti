package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;
import ru.daria.serverbeyti.service.ClientService;
import ru.daria.serverbeyti.service.WorkersService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ClientWorkersControllerTest {

    @Mock
    private ClientService clientService;
    @Mock
    private WorkersService workerService;

    @InjectMocks
    private ClientWorkersController clientWorkersController;

    @Test
    public void testGetWorkersByClientId_Found() {
        Long clientId = 1L;
        List<Workers> workers = List.of(new Workers(), new Workers());

        when(clientService.getWorkers(anyLong())).thenReturn(workers);

        ResponseEntity<List<Workers>> response = clientWorkersController.getWorkersByClientId(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(workers, response.getBody());
    }

    @Test
    public void testGetWorkersByClientId_NotFound() {

        Long clientId = 2L;

        when(clientService.getWorkers(anyLong())).thenReturn(null);

        ResponseEntity<List<Workers>> response = clientWorkersController.getWorkersByClientId(clientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testGetClientsByWorkerId_Found() {

        Long workerId = 1L;
        Workers worker = new Workers();
        List<Client> clients = List.of(new Client(), new Client());
        worker.setClients(clients);

        when(workerService.findByIdWorker(anyLong())).thenReturn(Optional.of(worker));

        ResponseEntity<List<Client>> response = clientWorkersController.getClientsByWorkerId(workerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    public void testGetClientsByWorkerId_NotFound() {
        Long workerId = 2L;

        when(workerService.findByIdWorker(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<List<Client>> response = clientWorkersController.getClientsByWorkerId(workerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testFindClientsByName_Found() {
        String name = "John";
        List<Client> clients = List.of(new Client(), new Client());
        when(clientService.findByNameClient(anyString())).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientWorkersController.findClientsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    public void testFindClientsByName_NotFound() {
        String name = "Unknown";
        when(clientService.findByNameClient(anyString())).thenReturn(Collections.emptyList());

        ResponseEntity<List<Client>> response = clientWorkersController.findClientsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
