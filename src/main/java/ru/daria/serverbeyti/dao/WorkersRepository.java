package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkersRepository extends JpaRepository<Workers, Long> {
    Optional<Workers> findBySurname(String surname);

    List<Workers> findAllByPost(String post);

    @Query("SELECT w FROM Workers w JOIN w.clients c WHERE c.id = :clientId")
    List<Workers> findWorkersByClientId(@Param("clientId") Long clientId);
}