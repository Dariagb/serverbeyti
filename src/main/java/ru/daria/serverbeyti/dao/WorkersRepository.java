package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.model.Workers;


import java.util.List;
import java.util.Optional;

@Repository
public interface WorkersRepository extends JpaRepository<Workers, Long> {
    Optional<Workers> findBySurname(String surname);
    Optional<Workers> findByPost(String post);
    List<Workers> findAllByPost(String post);
}