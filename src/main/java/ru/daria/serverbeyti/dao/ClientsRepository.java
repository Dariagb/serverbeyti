package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

    List<Workers> getWorkersById(Long clientId);

}
