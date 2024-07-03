package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;
import ru.daria.serverbeyti.service.exception.InsufficientVolumeException;
import ru.daria.serverbeyti.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void saveProduct() {
        Product product = new Product();
        productRepository.save(product);
    }

    public void getProductByShadeNumber(String name, Long shadeNumber, Long volume) throws InsufficientVolumeException, ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByShadeNumber(shadeNumber);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
           Long currentVolume = product.getVolume();
            if (volume < currentVolume) {
                product.setVolume(currentVolume - volume);
                productRepository.save(product);
            } else {
                throw new InsufficientVolumeException("Запрошенный вами объем больше, чем есть в наличии");
            }
        } else {
            throw new ProductNotFoundException("Продукт с номером: " + shadeNumber + " отсутствует");
        }
    }

//    public List<ProductDTO> readAllProductDTO() {
//        List<Product> products = productRepository.findAll();
//        return ProductMapper.INSTANCE.toProductDTOs(products);
//    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
//
//    public Product createProductDTO(ProductDTO dto) {
//        Product product = Product.builder()
//                .name(dto.getName())
//                .volume(dto.getVolume())
//                .shadeNumber(dto.getShadeNumber())
//                .build();
//        return productRepository.save(product);
//    }
public Product createProduct(ProductDTO dto) {
    Product product = productMapper.toProduct(dto);
    return productRepository.save(product);
}
    public List<ProductDTO> readAllProductDTO() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductDTOs(products);
    }
//public Product createProduct(ProductDTO dto) {
//    Product product = ProductMapper.INSTANCE.toProduct(dto);
//    return productRepository.save(product);
//}

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
