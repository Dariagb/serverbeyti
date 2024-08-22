package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ProductRepositoryTest extends AbstractSpringBootTest {
//    @Autowired
//    private ProductRepository productRepository;
//    private ProductMapper productMapper;

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

        // Act
        Optional<Product> product1 = productRepository.findByShadeNumberAndName(product.getShadeNumber(), product.getName());

        // Assert
        assertTrue(product1.isPresent());
        assertEquals(product1.get().getName(), product.getName());
        assertEquals(product1.get().getShadeNumber(), product.getShadeNumber());
        assertEquals(product1.get().getVolume(), product.getVolume());
    }

//    @Test
//    void ProductRepository_getPaintByShadeNumberAndName_test() {
//        Product product2 = Product.builder()
//                .name("Kapous")
//                .shadeNumber(42L)
//                .volume(37l)
//                .build();
//        productRepository.save(product2);
//
//        Optional<ProductDTO> productDTO = productRepository.getPaintByShadeNumberAndName(product2.getName(), product2.getShadeNumber());
//
//        assertTrue(productDTO.isPresent(), "Product not found");
//        ProductDTO dto = productDTO.get();
//        assertEquals(product2.getName(), dto.getName(), "Name mismatch");
//        assertEquals(product2.getShadeNumber(), dto.getShadeNumber(), "Shade number mismatch");
//        assertEquals(product2.getVolume(), dto.getVolume(), "Volume mismatch");
//    }

    @Test
    void ProductRepository_updatePaint_test() {
        Product product = Product.builder()
                .name("Estel")
                .shadeNumber(45L)
                .volume(34L)
                .build();
        productRepository.save(product);

        Long newVolume = 50L;
        productRepository.updatePaint(product.getName(), product.getShadeNumber(), newVolume);

        Optional<Product> updatedProduct = productRepository.findByShadeNumberAndName(product.getShadeNumber(), product.getName());

        assertTrue(updatedProduct.isPresent());
        assertEquals(updatedProduct.get().getVolume(), newVolume);
    }
}



