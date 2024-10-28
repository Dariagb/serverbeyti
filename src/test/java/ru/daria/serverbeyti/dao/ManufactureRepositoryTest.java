package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.model.Manufacturer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class ManufactureRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Test
    @Rollback
    void testGetJoinInformation() {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Астория");

        List<OrderResponse> joinInformation = manufactureRepository.getJoinInformation();

        assertNotNull(joinInformation);
        assertTrue(joinInformation.size() > 0, "Должна быть хотя бы одна запись");

    }
}
