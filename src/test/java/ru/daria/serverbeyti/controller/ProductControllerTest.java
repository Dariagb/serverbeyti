package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductControllerTest extends AbstractSpringBootTest {

    @Autowired
    private ReservationController controller;

    @Test
    void productController_сreatePaint_test() {

        ProductDTO dto = new ProductDTO();
        dto.setName("Olin");
        dto.setShadeNumber(123L);
        dto.setVolume(1000L);

        Product product = new Product();
        product.setName("Olin");
        product.setShadeNumber(123L);
        product.setVolume(1000L);

        when(productService.createProductPoint(dto)).thenReturn(product);

        ResponseEntity<Product> response = controller.createPaint(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProductPoint(dto);
    }

    @Test
    void productController_updatePaint_test() {
        String name = "Olin";
        Long shadeNumber = 12L;
        Long volume = 100L;

        ResponseEntity<?> response = controller.updatePaint(name, shadeNumber, volume);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productRepository, times(1)).updatePaint(name, shadeNumber, volume);
    }

    @Test
    void productController_updateProductPoint_test() {
        Product product = new Product();
        product.setName("Olin");
        product.setShadeNumber(3L);
        product.setVolume(10L);
        when(productService.updateProductPaint(product)).thenReturn(product);

        ResponseEntity<Product> response = controller.updateProductPoint(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProductPaint(product);
    }

    @Test
    void productController_readAllPaint_test() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO dto1 = new ProductDTO();
        dto1.setName("Olin");
        dto1.setShadeNumber(2L);
        dto1.setVolume(18L);

        ProductDTO dto2 = new ProductDTO();
        dto2.setName("Matrix");
        dto2.setShadeNumber(456L);
        dto2.setVolume(2000L);
        productDTOs.add(dto1);
        productDTOs.add(dto2);

        when(productService.readAllProductDTO()).thenReturn(productDTOs);

        ResponseEntity<List<ProductDTO>> response = controller.readAllPaint();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTOs, response.getBody());
        verify(productService, times(1)).readAllProductDTO();
    }

    @Test
    void productController_getPaint_test() {
        String name = "Olin";
        Long shadeNumber = 78L;


        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setShadeNumber(shadeNumber);
        dto.setVolume(67L);


        Product product = new Product();
        product.setName(name);
        product.setShadeNumber(shadeNumber);
        product.setVolume(67L);

        when(productService.getPaintByShadeNumberAndName(shadeNumber, name)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = controller.getPaint(shadeNumber, name);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService, times(1)).getPaintByShadeNumberAndName(shadeNumber, name);
    }

    @Test
    void productController_getPaintNotFound_test() {
        String name = "Olin";
        Long shadeNumber = 123L;

        when(productService.getPaintByShadeNumberAndName(shadeNumber, name)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = controller.getPaint(shadeNumber, name);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService, times(1)).getPaintByShadeNumberAndName(shadeNumber, name);
    }

    @Test
    void productController_deletePaint_test() {
        Long id = 1L;

        HttpStatus status = controller.deletePaint(id);

        assertEquals(HttpStatus.OK, status);
        verify(productService, times(1)).deleteProductById(id);
    }

}