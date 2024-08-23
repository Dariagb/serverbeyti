package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.exception.InsufficientVolumeException;
import ru.daria.serverbeyti.exception.ProductNotFoundException;
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

    public Product getProductByShadeNumberAndName(Long shadeNumber, String name, Long volume) throws InsufficientVolumeException, ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByShadeNumberAndName(shadeNumber, name);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Long currentVolume = product.getVolume();
            if (volume <= currentVolume) {
                product.setVolume(currentVolume - volume);
                return productRepository.save(product);
            } else {
                throw new InsufficientVolumeException("Запрошенный вами объем больше, чем есть в наличии");
            }
        } else {
            throw new ProductNotFoundException("Продукт с номером: " + shadeNumber + " отсутствует");
        }
    }

    public Optional<ProductDTO> getPaintByShadeNumberAndName(String name, Long shadeNumber) {
        return productRepository.getPaintByShadeNumberAndName(name, shadeNumber);
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

    public boolean deleteProductById(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
