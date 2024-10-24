package ru.daria.serverbeyti.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Import(ManufactureRepository.class)
public class ManufactureRepositoryTest {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Mock
    private Manufacturer mockManufacturer;

    @BeforeEach
    void setUp() {

    }
    @Test
    public void testGetJoinInformation() {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(1L);
        manufacturer.setName("Test Manufacturer");
        manufacturer.setProducts(Arrays.asList(
                new Product("Product A", 1L, 10L, 100L, manufacturer),
                new Product("Product B", 2L, 20L, 200L, manufacturer)
        ));


        List<OrderResponse> expectedResponses = Arrays.asList(
                new OrderResponse(manufacturer.getName(), "Product A"),
                new OrderResponse(manufacturer.getName(), "Product B")
        );

        when(manufactureRepository.getJoinInformation()).thenReturn(expectedResponses);


        List<OrderResponse> actualResponses = manufactureRepository.getJoinInformation();

        assertThat(actualResponses).hasSize(2);
        assertThat(actualResponses).containsAll(expectedResponses);
    }
}