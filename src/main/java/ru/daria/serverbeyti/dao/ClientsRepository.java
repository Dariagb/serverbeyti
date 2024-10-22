package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);
}
