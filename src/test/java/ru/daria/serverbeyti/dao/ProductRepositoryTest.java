package ru.daria.serverbeyti.dao;

import org.junit.jupiter.api.Test;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.model.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductRepositoryTest extends AbstractSpringBootTest {

    @Test
    void productRepository_findByShadeNumberAndName_test() {
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
    void productRepository_updatePaint_test() {
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