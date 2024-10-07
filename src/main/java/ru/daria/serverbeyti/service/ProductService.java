package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> getPaintByShadeNumberAndName(Long shadeNumber, String name) {
        return productRepository.findByShadeNumberAndName(shadeNumber, name);
    }

    public Product updateProductPaint(Product product) {
        return productRepository.save(product);
    }

    public Product createProductPoint(ProductDTO dto) {
        Product product = productMapper.toProduct(dto);
        return productRepository.save(product);
    }

    public List<ProductDTO> readAllProductDTO() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductDTOs(products);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
