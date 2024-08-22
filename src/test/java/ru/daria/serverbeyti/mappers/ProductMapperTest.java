package ru.daria.serverbeyti.mappers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testToProductDTO() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Olin");
        product.setShadeNumber(45l);
        product.setVolume(67l);

        ProductDTO productDTO = productMapper.toProductDTO(product);

        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getShadeNumber(), productDTO.getShadeNumber());
        assertEquals(product.getVolume(), productDTO.getVolume());
    }

    @Test
    public void testToProductDTOs() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Olin");
        product1.setShadeNumber(45l);
        product1.setVolume(67l);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Estel");
        product2.setShadeNumber(48l);
        product2.setVolume(69l);

        List<Product> products = Arrays.asList(product1, product2);

        List<ProductDTO> productDTOs = productMapper.toProductDTOs(products);


        assertEquals(2, productDTOs.size());
        assertEquals(product1.getName(), productDTOs.get(0).getName());
        assertEquals(product1.getShadeNumber(), productDTOs.get(0).getShadeNumber());
        assertEquals(product1.getVolume(), productDTOs.get(0).getVolume());
        assertEquals(product2.getName(), productDTOs.get(1).getName());
        assertEquals(product2.getShadeNumber(), productDTOs.get(1).getShadeNumber());
        assertEquals(product2.getVolume(), productDTOs.get(1).getVolume());
    }

    @Test
    public void testToProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Olin");
        productDTO.setShadeNumber(45l);
        productDTO.setVolume(67l);

        Product product = productMapper.toProduct(productDTO);

        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getShadeNumber(), product.getShadeNumber());
        assertEquals(productDTO.getVolume(), product.getVolume());
    }
}
