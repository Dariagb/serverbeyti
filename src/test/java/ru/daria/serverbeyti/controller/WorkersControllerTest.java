package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.model.Workers;
import ru.daria.serverbeyti.service.WorkersService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkersControllerTest {

    @Mock
    private WorkersService workersServise;

    @Mock
    private WorkersRepository workersRepository;

    @InjectMocks
    private WorkersController workersController;

    @Test
    void createWorker() {
        Workers worker =  Workers.builder()
                .name("Полина")
                .surname("Графова")
                .post("мастер маниккюра")
                .age(45)
                .phone("56789")
                .build();
        when(workersServise.createWorkers(worker)).thenReturn(worker);

        ResponseEntity<Workers> response = workersController.createWorker(worker);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(worker, response.getBody());
    }

    @Test
    void readAllWorkers() {
        List<Workers> workers = Arrays.asList(
                Workers.builder()
                        .name("Полина")
                        .surname("Графова")
                        .post("мастер маниккюра")
                        .age(45)
                        .phone("56789")
                        .build(),
                Workers.builder()
                        .name("Ольга")
                        .surname("Панова")
                        .post("мпарикмахер")
                        .age(45)
                        .phone("56789")
                        .build()
        );
        when(workersServise.readAllWorkers()).thenReturn(workers);

        ResponseEntity<List<Workers>> response = workersController.readAllWorkers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(workers, response.getBody());
    }

    @Test
    void getWorkersByPost() {
        List<Workers> workers = Arrays.asList(
                Workers.builder()
                        .name("Полина")
                        .surname("Графова")
                        .post("мастер маниккюра")
                        .age(45)
                        .phone("56789")
                        .build(),
                Workers.builder()
                        .name("Ольга")
                        .surname("Панова")
                        .post("парикмахер")
                        .age(45)
                        .phone("56789")
                        .build()
        );

        String post = "парикмахер";
        when(workersServise.getWorkersByPost(post)).thenReturn(workers);

        ResponseEntity<List<Workers>> response = workersController.getWorkersByPost(post);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(workers, response.getBody());
    }

    @Test
    void getWorkersByPost_NotFound() {
        String post = "Manager";
        when(workersServise.getWorkersByPost(post)).thenReturn(List.of());

        ResponseEntity<List<Workers>> response = workersController.getWorkersByPost(post);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getWorkersBySurname() {
        Workers worker =  Workers.builder()
                .name("Полина")
                .surname("Графова")
                .post("мастер маниккюра")
                .age(45)
                .phone("56789")
                .build();
        when(workersServise.getWorkersBySurname("Графова")).thenReturn(Optional.of(worker));

        ResponseEntity<Workers> response = workersController.getWorkersBySurname("Графова");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(worker, response.getBody());
    }

    @Test
    void getWorkersBySurname_NotFound() {
        String surname = "Попова";
        when(workersServise.getWorkersBySurname(surname)).thenReturn(Optional.empty());

        ResponseEntity<Workers> response = workersController.getWorkersBySurname(surname);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteWorker() {
        Long id = 1L;

        workersController.deleteWorker(id);

        Mockito.verify(workersServise, times(1)).deleteWorkersById(id);
    }
}