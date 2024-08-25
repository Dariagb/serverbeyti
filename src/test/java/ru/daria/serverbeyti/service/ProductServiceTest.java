package ru.daria.serverbeyti.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository, productMapper);
    }

    @Test
    public void ProductService_saveProduct_Test() {
        Product product = Product.builder()
                .name("Estel")
                .shadeNumber(45L)
                .volume(34l)
                .build();

        productService.saveProduct(product);

        verify(productRepository).save(product);
    }

//    @Test
//    public void ProductService_getPaintByShadeNumberAndName_Found_Test() {
//        String name = "Estel";
//        Long shadeNumber = 123L;
//        Product expectedProduct = new Product();
//        expectedProduct.setName(name);
//        expectedProduct.setShadeNumber(shadeNumber);
//        ProductDTO expectedProductDTO = new ProductDTO(expectedProduct);
//        when(productRepository.findByShadeNumberAndName(shadeNumber, name)).thenReturn(Optional.of(expectedProduct));
//
//        Optional<ProductDTO> actualProductDTO = productService.getPaintByShadeNumberAndName(name, shadeNumber);
//
//        assertTrue(actualProductDTO.isPresent());
//        assertEquals(expectedProductDTO, actualProductDTO.get());
//    }

//    @Test
//    public void ProductService_GetPaintByShadeNumberAndName_NotFound_Test() {
//        String name = "Olin";
//        Long shadeNumber = 456L;
//        when(productRepository.findByShadeNumberAndName(shadeNumber,name)).thenReturn(Optional.empty());
//
//        Optional<ProductDTO> actualProductDTO = productService.getPaintByShadeNumberAndName(name,shadeNumber);
//        assertFalse(actualProductDTO.isPresent());
//    }

    @Test
    public void ProductService_updateProductPaint_Test() {
        Product productToUpdate = Product.builder()
                .name("Estel")
                .shadeNumber(49L)
                .volume(49l)
                .build();

        Product savedProduct = Product.builder()
                .name("Estel")
                .shadeNumber(49L)
                .volume(50l)
                .build();

        when(productRepository.save(productToUpdate)).thenReturn(savedProduct);

        Product updatedProduct = productService.updateProductPaint(productToUpdate);

        assertEquals(savedProduct, updatedProduct);
        verify(productRepository).save(productToUpdate);
    }

    @Test
    public void ProductService_createProductPoint_Test() {
        ProductDTO productDTO = ProductDTO.builder().name("Estel").volume(34L).shadeNumber(45L).build();
        Product product = Product.builder()
                .name("Estel")
                .volume(34L)
                .shadeNumber(45L)
                .build();
        when(productMapper.toProduct(productDTO)).thenReturn(product);
        when(productRepository.save(Mockito.argThat(arg -> arg.equals(product)))).thenReturn(product);

        Product createdProduct = productService.createProductPoint(productDTO);

        assertEquals(product, createdProduct);
        verify(productMapper).toProduct(productDTO);

    }

    @Test
    public void ProductService_readAllProductDTO_Test() {

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("Kapous").volume(87L).shadeNumber(4L).build());
        products.add(Product.builder().name("Matrix").volume(70L).shadeNumber(48L).build());

        List<ProductDTO> productDTOs = new ArrayList<>();
        productDTOs.add(ProductDTO.builder().name("Kapous").volume(87L).shadeNumber(4L).build());
        productDTOs.add(ProductDTO.builder().name("Matrix").volume(70L).shadeNumber(48L).build());

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toProductDTOs(products)).thenReturn(productDTOs);

        List<ProductDTO> result = productService.readAllProductDTO();

        assertEquals(productDTOs, result);
        verify(productRepository).findAll();
        verify(productMapper).toProductDTOs(Mockito.argThat(arg -> arg.equals(products)));
    }

    @Test
    void ProductService_deleteProductById_Test() {
        Long productId = 1L;
        productService.deleteProductById(productId);
        verify(productRepository).deleteById(productId);
    }
}