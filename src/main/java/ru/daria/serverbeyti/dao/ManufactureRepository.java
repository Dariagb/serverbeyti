package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.model.Manufacturer;


import java.util.List;
@Repository
public interface ManufactureRepository extends JpaRepository<Manufacturer, Long> {

    @Query("SELECT p.name, m.name FROM Manufacturer m JOIN m.products p")
    List<OrderResponse> getJoinInformation();
}
