package ru.daria.serverbeyti.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.model.Workers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkersServiceTest {

    @Mock
    private WorkersRepository workersRepository;

    @InjectMocks
    private WorkersService workersServise;

    @Test
    void getWorkersBySurname() {
        String surname = "Smith";
        Workers worker = new Workers();
        worker.setSurname(surname);

        when(workersRepository.findBySurname(surname)).thenReturn(Optional.of(worker));

        Optional<Workers> result = workersServise.getWorkersBySurname(surname);

        assertTrue(result.isPresent());
        assertEquals(result.get(), worker);
    }

    @Test
    void createWorkers() {
        Workers worker = new Workers();

        when(workersRepository.save(worker)).thenReturn(worker);

        Workers createdWorker = workersServise.createWorkers(worker);

        assertEquals(createdWorker, worker);
        verify(workersRepository, times(1)).save(worker);
    }

    @Test
    void readAllWorkers() {
        List<Workers> workers = Arrays.asList(new Workers(), new Workers());

        when(workersRepository.findAll()).thenReturn(workers);

        List<Workers> result = workersServise.readAllWorkers();

        assertEquals(result, workers);
        verify(workersRepository, times(1)).findAll();
    }

    @Test
    void deleteWorkersById() {
        Long id = 1L;

        workersServise.deleteWorkersById(id);

        verify(workersRepository, times(1)).deleteById(id);
    }

    @Test
    void getWorkersByPost() {
        String post = "Лашмейкер";
        List<Workers> workers = Arrays.asList(new Workers(), new Workers()); // Create a list of workers

        when(workersRepository.findAllByPost(post)).thenReturn(workers);

        List<Workers> result = workersServise.getWorkersByPost(post);

        assertEquals(result, workers);
        verify(workersRepository, times(1)).findAllByPost(post);
    }
}