package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daria.serverbeyti.model.Manufacturer;

public interface ManufactureRepository extends JpaRepository<Manufacturer,Long> {
}
