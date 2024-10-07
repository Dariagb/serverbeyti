package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daria.serverbeyti.model.Client;

public interface ClientsRepository extends JpaRepository<Client, Long> {
}
