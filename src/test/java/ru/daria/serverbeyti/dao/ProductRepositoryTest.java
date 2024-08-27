package ru.daria.serverbeyti.dao;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void ProductRepository_findByShadeNumberAndName_test() {
        Product product = Product.builder()
                .name("Estel")
                .shadeNumber(45L)
                .volume(34l)
                .build();
        Product product2 = Product.builder()
                .name("Kapous")
                .shadeNumber(42L)
                .volume(37l)
                .build();
        productRepository.save(product);
        productRepository.save(product2);

        when(productRepository.findByShadeNumberAndName(product.getShadeNumber(), product.getName()))
                .thenReturn(Optional.of(product));


        Optional<Product> product1 = productRepository.findByShadeNumberAndName(product.getShadeNumber(), product.getName());


        assertTrue(product1.isPresent());
        assertEquals(product1.get().getName(), product.getName());
        assertEquals(product1.get().getShadeNumber(), product.getShadeNumber());
        assertEquals(product1.get().getVolume(), product.getVolume());
    }

    @Test
    void ProductRepository_getPaintByShadeNumberAndName_test() {
        Product product2 = Product.builder()
                .name("Kapous")
                .shadeNumber(4L)
                .volume(38l)
                .build();

        ProductDTO expectedProductDTO = productMapper.toProductDTO(product2); // Map to DTO
        when(productRepository.getPaintByShadeNumberAndName(product2.getName(), product2.getShadeNumber()))
                .thenReturn(Optional.of(expectedProductDTO));

        Optional<ProductDTO> productDTO = productRepository.getPaintByShadeNumberAndName(product2.getName(), product2.getShadeNumber());

        assertTrue(productDTO.isPresent(), "Товар не найден");
        ProductDTO dto = productDTO.get();
        assertEquals(product2.getName(), dto.getName(), "Названия не соответствуют");
        assertEquals(product2.getShadeNumber(), dto.getShadeNumber(), "Номер оттенка не соответствует");
        assertEquals(product2.getVolume(), dto.getVolume(), "Объем не соответствует");
    }

    @Test
    void ProductRepository_updatePaint_test() {
        Product product = Product.builder()
                .name("Estel")
                .shadeNumber(5L)
                .volume(38L)
                .build();

        Long newVolume = 50L;

        doNothing().when(productRepository).updatePaint(product.getName(), product.getShadeNumber(), newVolume);

        productRepository.updatePaint(product.getName(), product.getShadeNumber(), newVolume);

        verify(productRepository).updatePaint(product.getName(), product.getShadeNumber(), newVolume);
    }
}