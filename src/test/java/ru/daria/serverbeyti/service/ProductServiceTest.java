package ru.daria.serverbeyti.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.ArrayList;
import java.util.List;

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
    void productService_saveProduct_Test() {
        Product product = Product.builder()
                .name("Estel")
                .shadeNumber(45L)
                .volume(34l)
                .build();

        productService.saveProduct(product);

        verify(productRepository).save(product);
    }

    @Test
    void productService_updateProductPaint_Test() {
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
    void productService_createProductPoint_Test() {
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
    void productService_readAllProductDTO_Test() {

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
    void productService_deleteProductById_Test() {
        Long productId = 1L;
        productService.deleteProductById(productId);
        verify(productRepository).deleteById(productId);
    }
}


