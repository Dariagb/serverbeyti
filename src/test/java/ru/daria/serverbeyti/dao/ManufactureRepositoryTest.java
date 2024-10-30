package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManufactureRepositoryTest extends AbstractSpringBootTest{

    @Test
    @Transactional
    public void testGetJoinInformation() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Косметик групп");
        manufactureRepository.save(manufacturer);

        Product product = new Product();
        product.setName("Estel");
        product.setManufacturer(manufacturer);
        productRepository.save(product);

        List<OrderResponse> joinInformation = manufactureRepository.getJoinInformation();

        assertNotNull(joinInformation);
        assertFalse(joinInformation.isEmpty(), "Должна быть хотя бы одна запись");

        assertEquals("Estel", joinInformation.get(0).getProductName());
        assertEquals("Косметик групп", joinInformation.get(0).getManufacturerName());
    }
}






