package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkersService {

    private final WorkersRepository workersRepository;

    public Optional<Workers> getWorkersBySurname(String surname) {
        return workersRepository.findBySurname(surname);
    }

    public Workers createWorkers(Workers workers) {
        return workersRepository.save(workers);
    }

    public List<Workers> readAllWorkers() {
        return workersRepository.findAll();
    }

    public void deleteWorkersById(Long id) {
        workersRepository.deleteById(id);
    }

    public List<Workers> getWorkersByPost(String post) {
        return workersRepository.findAllByPost(post);
    }
}
