package ru.daria.serverbeyti.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.TestBeans;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;
import ru.daria.serverbeyti.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductControllerTest extends AbstractSpringBootTest {

    @Autowired
    private ProductController controller;

    @Test
    public void ProductController_сreatePaint_test() {

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
    public void ProductController_UpdatePaint_test() {
        String name = "Olin";
        Long shadeNumber = 123L;
        Long volume = 1000L;

        ResponseEntity<?> response = controller.updatePaint(name, shadeNumber, volume);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productRepository, times(1)).updatePaint(name, shadeNumber, volume);
    }

//    @Test
//    public void ProductController_UpdatePaintWithException_test() {
//        // Arrange
//        String name = "Olin";
//        Long shadeNumber = 123L;
//        Long volume = 1000L;
//        RuntimeException exception = new RuntimeException("Error ");
//
//        when(productRepository.updatePaint(name, shadeNumber, volume)).thenThrow(exception);
//
//        // Act
//        ResponseEntity<?> response = controller.updatePaint(name, shadeNumber, volume);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(exception.getMessage(), response.getBody());
//        verify(productRepository, times(1)).updatePaint(name, shadeNumber, volume);
//    }

    @Test
    public void ProductController_UpdateProductPoint_test() {
        Product product = new Product();
        product.setName("Olin");
        product.setShadeNumber(123L);
        product.setVolume(1000L);
        when(productService.updateProductPaint(product)).thenReturn(product);

        ResponseEntity<Product> response = controller.updateProductPoint(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProductPaint(product);
    }

    @Test
    public void ProductController_ReadAllPaint_test() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO dto1 = new ProductDTO();
        dto1.setName("Olin");
        dto1.setShadeNumber(123L);
        dto1.setVolume(1000L);

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
    public void ProductController_GetPaint_test() {
        String name = "Olin";
        Long shadeNumber = 123L;
        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setShadeNumber(shadeNumber);
        dto.setVolume(1000L);

        when(productService.getPaintByShadeNumberAndName(shadeNumber,name)).thenReturn(Optional.of(dto));

        ResponseEntity<ProductDTO> response = controller.getPaint(shadeNumber,name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(productService, times(1)).getPaintByShadeNumberAndName(shadeNumber,name);
    }

    @Test
    public void ProductController_GetPaintNotFound_test() {
        String name = "Olin";
        Long shadeNumber = 123L;

        when(productService.getPaintByShadeNumberAndName(shadeNumber,name)).thenReturn(Optional.empty());

        ResponseEntity<ProductDTO> response = controller.getPaint(shadeNumber,name);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService, times(1)).getPaintByShadeNumberAndName(shadeNumber,name);
    }

    @Test
    public void ProductController_DeletePaint_test() {
        Long id = 1L;

        HttpStatus status = controller.deletePaint(id);

        assertEquals(HttpStatus.OK, status);
        verify(productService, times(1)).deleteProductById(id);
    }
}