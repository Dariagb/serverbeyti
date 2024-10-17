package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.model.Manufacturer;

import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacturer, Long> {
    @Query(value = "SELECT new ru.daria.serverbeyti.dto.OrderResponse(p.name, m.name) " +
            "FROM Manufacturer m JOIN m.products p")
    List<OrderResponse> getJoinInformation();
}
